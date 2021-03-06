package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.response.GroupMessageResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2018/11/13
 */
@ChannelHandler.Sharable
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponse> {

    public static final GroupMessageResponseHandler INSTANCE = new GroupMessageResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponse msg) throws Exception {
        long userId = SessionUtils.getSession(ctx.channel()).getUserId();
        if (msg.isSuccess()) {
            if (msg.getSenderId() != userId) {
                System.out.println("收到用户" + msg.getSenderName() + "发来的群[" + msg.getGroupId() + "]消息：" + msg.getMessage());
            }
        } else {
            System.out.println(msg.getMessage());
        }
    }
}
