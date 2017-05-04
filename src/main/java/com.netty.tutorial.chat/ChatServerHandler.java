package com.netty.tutorial.chat;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

/**
 * Created by nthevj on 02.05.2017.
 */
public class ChatServerHandler extends SimpleChannelHandler {


    /**
     * helps us to fetch the message server received from client
     *
     * @param ctx
     * @param e   provides getMessage() method to retrieved message
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
        while (buf.readable()) {
            System.out.print((char) buf.readByte());
            System.out.flush();
        }
    }

    /**
     * helps you to get the exception while connecting or similar events
     *
     * @param ctx
     * @param e
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();

        Channel ch = e.getChannel();
        ch.close();
    }
}
