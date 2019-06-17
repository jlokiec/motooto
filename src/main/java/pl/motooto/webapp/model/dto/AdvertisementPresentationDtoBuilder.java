package pl.motooto.webapp.model.dto;

public class AdvertisementPresentationDtoBuilder {
    private AdvertisementPresentationDto dto;

    public AdvertisementPresentationDtoBuilder() {
        dto = new AdvertisementPresentationDto();
    }

    public AdvertisementPresentationDto build() {
        return dto;
    }

    public AdvertisementPresentationDtoBuilder setTitle(String title) {
        dto.setTitle(title);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setUsername(String username) {
        dto.setUsername(username);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setLastModificationDate(String lastModificationDate) {
        dto.setLastModificationDate(lastModificationDate);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setPriceInPln(String priceInPln) {
        dto.setPriceInPln(priceInPln);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setPriceInEur(String priceInEur) {
        dto.setPriceInEur(priceInEur);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setPriceInUsd(String priceInUsd) {
        dto.setPriceInUsd(priceInUsd);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setPriceInGbp(String priceInGbp) {
        dto.setPriceInGbp(priceInGbp);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setDescription(String description) {
        dto.setDescription(description);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setCarMake(String carMake) {
        dto.setCarMake(carMake);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setCarModel(String carModel) {
        dto.setCarModel(carModel);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setProductionYear(int productionYear) {
        dto.setProductionYear(productionYear);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setEngineDisplacement(int engineDisplacement) {
        dto.setEngineDisplacement(engineDisplacement);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setHorsePower(int horsePower) {
        dto.setHorsePower(horsePower);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setCarStatus(String carStatus) {
        dto.setCarStatus(carStatus);
        return this;
    }

    public AdvertisementPresentationDtoBuilder setPhoneNumber(String phoneNumber) {
        dto.setPhoneNumber(phoneNumber);
        return this;
    }
}
