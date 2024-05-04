package Kotasek.Contract.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ContractDTO {

    long Id;

    @NotBlank(message = "Vyplňte titulek")
    @Size(max = 100, message = "Název je příliš dlouhý")
    private String name;
    @NotBlank(message = "Vyplňte popis")
    private String description;
    @NotBlank(message = "Vyplňte zákazníka")
    private String customer;
    @NotNull(message = "Vyplňte cenu")
    private Float price;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

