package com.ead.payment.api.consumers;

import com.ead.payment.api.dtos.UserEventDTO;
import com.ead.payment.domain.enums.ActionType;
import com.ead.payment.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserConsumer {

    private final UserService userService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ead.broker.queue.userEventQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${ead.broker.exchange.userEventExchange}",
                    type = ExchangeTypes.FANOUT, ignoreDeclarationExceptions = "true")
    ))
    public void listenerUserEvent(@Payload UserEventDTO userEventDTO) {
        switch (ActionType.valueOf(userEventDTO.getActionType())) {
            case CREATE:
                userService.saveUser(userEventDTO);
                break;
            case UPDATE:
                userService.saveUser(userEventDTO);
                break;
            case DELETE:
                userService.deleteUser(userEventDTO.getUserId());
                break;
        }
    }
}
