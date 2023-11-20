package pe.edu.leadyourway.bikeservice.interfaces.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBikeResource {
    private Long userId;
    private String name;
    private String description;
    private Double price;
    private String model;
    private String imageUrl;
}
