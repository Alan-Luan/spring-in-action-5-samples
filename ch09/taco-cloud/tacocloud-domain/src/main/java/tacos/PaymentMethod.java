package tacos;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PaymentMethod {

  @ManyToOne private final User user;
  private final String ccNumber;
  private final String ccCVV;
  private final String ccExpiration;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
}
