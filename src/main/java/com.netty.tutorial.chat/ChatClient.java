package com.netty.tutorial.chat;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by nthevj on 02.05.2017.
 */
public class ChatClient {

    public static void main(String args[]) throws IOException {


        //create a Boss thread from thread pool
        //they do main work like connect, bind and pass them for real work to worker threads.
        Executor bossPool = Executors.newCachedThreadPool();
        //they do the real work
        Executor workerPool = Executors.newCachedThreadPool();

        //create NIO client channel factories
        ChannelFactory channelFactory = new NioClientSocketChannelFactory(bossPool, workerPool);


        //Client Setup the channel using bootstrap
        ClientBootstrap clientBootstrap = new ClientBootstrap(channelFactory);


//        //setup the Channel PipeLine Factory
//        ChannelPipelineFactory pipelineFactory = new ChannelPipelineFactory() {
//            public ChannelPipeline getPipeline() throws Exception {
//                return Channels.pipeline(
//                        new ObjectEncoder()
//                );
//            }
//        };
//
//        //set the pipeline
//        clientBootstrap.setPipelineFactory(pipelineFactory);


        // server socket address to which this client is supposed to connect to
        InetSocketAddress addressToConnectTo = new InetSocketAddress("localhost", 8090);

        //connect asynchronously :)
        ChannelFuture cf = clientBootstrap.connect(addressToConnectTo);

        //get the channel to send message
        org.jboss.netty.channel.Channel channel = cf.getChannel(); //could be a error here

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            //write data in it.
            channel.write(input.readLine().getBytes());

        }


    }
}
