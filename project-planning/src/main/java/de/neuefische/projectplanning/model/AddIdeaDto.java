package de.neuefische.projectplanning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddIdeaDto {

  @Size(min = 5, message = "description min length 5")
  private String description;

}
