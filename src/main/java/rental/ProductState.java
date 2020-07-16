package rental;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ProductState_table")
public class ProductState {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long itemId;
        private Integer price;
        private Integer amount;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }
        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

}
