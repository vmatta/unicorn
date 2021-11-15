package com.self.unicorn.domain;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("unicorns")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Unicorn {

  @Id
  private String unicornId;

  @NotEmpty(message = "name can't be empty")
  @Length(min = 1, max = 50)
  private String name;
  @NotNull(message = "hairColor is a mandatory field")
  private HairColor hairColor;

  private int hornLength;
  @NotNull(message = "hornColor is a mandatory field")
  private HornColor hornColor;
  @NotNull(message = "eyeColor is a mandatory field")
  private EyeColor eyeColor;
  private int height;
  @NotNull(message = "heightUnit is a mandatory field")
  private Unit heightUnit;
  private int weight;
  @NotNull(message = "weightUnit is a mandatory field")
  private Unit weightUnit;
  @NotNull
  private List<IdentifiableMark> identifiableMarks = new ArrayList<>(0);

}
