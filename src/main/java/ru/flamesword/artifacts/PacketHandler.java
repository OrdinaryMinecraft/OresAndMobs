package ru.flamesword.artifacts;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.StatCollector;

public class PacketHandler {

    private static final String DIVIDER = "/";

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) {
        if (event.packet.channel().equals(PacketChannel.ARTIFACTSOTHER.name())) {
            handleServerPacket(event.packet, ((NetHandlerPlayServer) event.handler).playerEntity);
        }
    }

    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
        if (event.packet.channel().equals(PacketChannel.ARTIFACTSOTHER.name()))
            handlePacket(event.packet);
    }

    private void handleServerPacket(FMLProxyPacket pckt, EntityPlayer entityPlayerMP) {
        String packetString = ByteBufUtils.readUTF8String(pckt.payload());
        if (packetString != null) {
            String[] parts = packetString.split(DIVIDER);
            int itemId = Integer.parseInt(parts[0]);
            short level = Short.parseShort(parts[1]);
            String from = parts[2];
            int x = Integer.parseInt(parts[3]);
            int y = Integer.parseInt(parts[4]);
            int z = Integer.parseInt(parts[5]);
            String localizedName = parts[6];
            ItemStack artifact = ArtifactsUtils.getArtifact(level, localizedName, itemId, from, entityPlayerMP.getDisplayName());
            EntityItem entityAftifact = new EntityItem(entityPlayerMP.worldObj, x, y, z, artifact);
            entityPlayerMP.worldObj.spawnEntityInWorld(entityAftifact);
        }
    }

    private void handlePacket(FMLProxyPacket pckt) {
        String packetString = ByteBufUtils.readUTF8String(pckt.payload());
        if (packetString != null) {
            String[] parts = packetString.split(DIVIDER);
            int itemId = Integer.parseInt(parts[0]);
            short level = Short.parseShort(parts[1]);
            String from = parts[2];
            int x = Integer.parseInt(parts[3]);
            int y = Integer.parseInt(parts[4]);
            int z = Integer.parseInt(parts[5]);
            Item item = Item.getItemById(itemId);
            String localizedName = "";
            if (item != null) {
                localizedName = StatCollector.translateToLocal(item.getUnlocalizedName() + ".name");
            }
            FMLProxyPacket packet = PacketHandler.getOtherPacket(Side.SERVER, itemId, level, from, x, y, z, localizedName);
            ArtifactsBase.otherChannel.sendToServer(packet);
        }
    }

    public static FMLProxyPacket getOtherPacket(Side side, int itemId, short level, String from, int x, int y, int z, String localizedName) {
        String data = itemId + DIVIDER + level + DIVIDER + from + DIVIDER + x + DIVIDER + y + DIVIDER + z + DIVIDER + localizedName;
        ByteBuf buf = Unpooled.buffer();
        ByteBufUtils.writeUTF8String(buf, data);
        FMLProxyPacket pkt = new FMLProxyPacket(buf, PacketChannel.ARTIFACTSOTHER.name());
        pkt.setTarget(side);
        return pkt;
    }
}
