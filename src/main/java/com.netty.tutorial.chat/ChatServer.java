package com.netty.tutorial.chat;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by nthevj on 02.05.2017.
 */
public class ChatServer {


    public static void main(String[] args) throws Exception {

        ChannelFactory factory =
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool());

        //create a basic setup of server
        ServerBootstrap bootstrap = new ServerBootstrap(factory);


        // set your channelHandler to ChannelPipeline so that it can process messageEvents.
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
                return Channels.pipeline(new ChatServerHandler());
            }
        });

        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);


        //Bind your server to run on a host and port.
        bootstrap.bind(new InetSocketAddress(8090));
    }
}
