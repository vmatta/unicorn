package com.self.unicorn.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IdentifiableMark {

  @NotNull(message = "side is a mandatory field")
  private Side side;
  @NotEmpty(message = "location can't be empty")
  @Length(min = 3, max = 30)
  private String location;
  @NotEmpty(message = "mark can't be empty")
  @Length(min = 3, max = 20)
  private String mark;
  @NotNull(message = "color can't be empty")
  private Color color;

}
