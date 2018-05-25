package com.dhlk.bas.netty;

import com.dhlk.bas.model.CarbonDioxide;
import com.dhlk.bas.util.HexUtil;
import com.dhlk.bas.util.SocketUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fnan on 2017-11-09.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    // 本地化存储
    public static Map<String, ChannelHandlerContext> ctxMap = new HashMap<>();
    public static CarbonDioxide carbonDioxide = new CarbonDioxide();

    /**
     * channelRegistered
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        if (ctx != null) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
            String host = inetSocketAddress.getAddress().getHostAddress();
            ctxMap.put(host, ctx);
        }

    }

    /**
     * channelUnregistered
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

        if (ctx != null) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
            String host = inetSocketAddress.getAddress().getHostAddress();
            if (ctxMap.containsKey(host)) {
                ctxMap.remove(host);
            }
        }

    }

    /**
     * exceptionCaught
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        if (ctx != null) {
            ctx.flush();
            ctx.close();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)

        byte[] req;
        try {
            ByteBuf buf = (ByteBuf) msg;
            req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            buf.release();
        } catch (Exception e) {
//            e.printStackTrace();
            return;
        }

        // hex转换
        String hex = HexUtil.byteToHex(req);
//        System.out.println("hex:" + hex);

        // CarbonDioxide Test
//        if (hex.startsWith("0103") && hex.length() == 22) {
//            carbonDioxide.setHumidity(HexUtil.hex2Decimal(hex.substring(6,10))/10.0f);
//            carbonDioxide.setTemperature(HexUtil.hex2Decimal(hex.substring(10,14))/10.0f);
//            carbonDioxide.setCarbon_dioxide_concentration(HexUtil.hex2Decimal(hex.substring(14,18)));
//            System.out.println(carbonDioxide.toString());
//        }

        int start;
        start = hex.indexOf("55bb");
        if (start > 0) {
            hex = hex.substring(start);
        }

        try {
            while (hex.length() >= 116) {
                String message = hex.substring(0, 116);
                if (message.startsWith("55bb")) {
//                    System.out.println(message);
                    SocketUtil.dispatchMessage(message);
                }
                hex = hex.substring(116);
            }
        } finally {
            ReferenceCounted msg1 = (ReferenceCounted) msg;
            int refCnt = msg1.refCnt();
            if (refCnt > 0) {
                ReferenceCountUtil.release(msg); // (2)
            }
            ctx.flush();
//            ctx.close();
        }

    }

}
