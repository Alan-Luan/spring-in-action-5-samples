package tacos;

import com.datastax.driver.core.utils.UUIDs;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("payment_method")
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PaymentMethod {

  private final UserUDT user;
  private final String ccNumber;
  private final String ccCVV;
  private final String ccExpiration;

  @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
  private UUID id = UUIDs.timeBased();
}
