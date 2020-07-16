package rental;

import rental.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductStateViewHandler {


    @Autowired
    private ProductStateRepository productStateRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenItemCreated_then_CREATE_1 (@Payload ItemCreated itemCreated) {
        try {
            if (itemCreated.isMe()) {
                // view 객체 생성
                ProductState productState = new ProductState();
                // view 객체에 이벤트의 Value 를 set 함
                productState.setPrice(itemCreated.getPrice());
                productState.setItemId(itemCreated.getId());
                // view 레파지 토리에 save
                productStateRepository.save(productState);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenItemProduced_then_UPDATE_1(@Payload ItemProduced itemProduced) {
        try {
            if (itemProduced.isMe()) {
                // view 객체 조회
                List<ProductState> productStateList = productStateRepository.findByItemId(itemProduced.getId());
                for(ProductState productState : productStateList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    productState.setAmount(productState.getAmount()+itemProduced.getAmount());
                    // view 레파지 토리에 save
                    productStateRepository.save(productState);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenProductChanged_then_UPDATE_2(@Payload ProductChanged productChanged) {
        try {
            if (productChanged.isMe()) {
                // view 객체 조회
                List<ProductState> productStateList = productStateRepository.findByItemId(productChanged.getId());
                for(ProductState productState : productStateList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    productState.setAmount(productState.getAmount() + productChanged.getAmount());
                    // view 레파지 토리에 save
                    productStateRepository.save(productState);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
