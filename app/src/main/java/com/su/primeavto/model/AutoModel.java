package com.su.primeavto.model;

import com.google.gson.annotations.SerializedName;
import com.su.primeavto.App;
import com.su.primeavto.R;

import java.io.Serializable;

public class AutoModel implements Serializable {

    @SerializedName("NAME")
    private String name;
    @SerializedName("CODE")
    private String code;
    @SerializedName("URL")
    private String url;
    @SerializedName("PICTURE")
    private String picturePath;
    @SerializedName("TEXT")
    private String text;
    @SerializedName("YEAR")
    private NameValueModel year;
    @SerializedName("ENGINE")
    private NameValueModel engine;
    @SerializedName("COLOR")
    private NameValuesModel color;
    @SerializedName("EQUIPMENT")
    private NameValueModel equipment;
    @SerializedName("SUSPENSION")
    private NameValueModel suspension;
    @SerializedName("SALON")
    private NameValueModel salon;
    @SerializedName("KPP")
    private NameValueModel kpp;
    @SerializedName("DRIVE_UNIT")
    private NameValueModel driveUnit;
    @SerializedName("FUEL")
    private NameValueModel fuel;
    @SerializedName("NUMBER_OF_PLACE")
    private NameValueModel placeNumber;
    @SerializedName("ENTERTAINMENT")
    private NameValuesModel entertainment;
    @SerializedName("ABS")
    private NameValueModel abs;
    @SerializedName("CONDITIONING")
    private NameValueModel conditioning;
    @SerializedName("POWER_STEERING")
    private NameValueModel powerSteering;
    @SerializedName("ASSISTANCE")
    private NameValuesModel assistance;
    @SerializedName("MIN_STAZH")
    private NameValueModel minExperience;
    @SerializedName("MIN_AGE")
    private NameValueModel minAge;
    @SerializedName("DEPOSIT")
    private NameValueModel deposit;
    @SerializedName("MILAGE")
    private NameValueModel mileage;
    @SerializedName("RENT_TERMS")
    private NameValueModel rentTerms;
    @SerializedName("TARIFF_RULES")
    private NameValueModel tariffRules;
    @SerializedName("ONE_DAY_PRICE")
    private NameValueModel oneDayPrice;
    @SerializedName("DOP_FOTO")
    private NameValuesModel additionalPhotos;
    @SerializedName("SUV")
    private NameValueModel suv;
    @SerializedName("NEW")
    private NameValueModel novelty;
    @SerializedName("STOCK")
    private NameValueModel stock;
    @SerializedName("DELIVERY")
    private NameValueModel delivery;
    @SerializedName("UNLIMITED_TARIFF")
    private NameValueModel unlimitedTariff;
    @SerializedName("REMOVE_FRANCHISE")
    private NameValueModel removeFranchise;
    @SerializedName("WASHING")
    private NameValueModel washing;
    @SerializedName("ZABOR")
    private NameValueModel refund;
    @SerializedName("APPMOBILE")
    private NameValueModel appMobile;
    @SerializedName("TWO_FIVE_DAY_PRICE")
    private NameValueModel twoFiveDayPrice;
    @SerializedName("THREE_FIFTH_DAY_PRICE")
    private NameValueModel threeFifteenDayPrice;
    @SerializedName("FIFTH_THIRTY_DAY_PRICE")
    private NameValueModel fifteenThirtyDayPrice;
    @SerializedName("FIFTH_DAY_PRICE")
    private NameValueModel fifteenDayPrice;
    @SerializedName("DAY_OFF_PRICE")
    private NameValueModel dayOffPrice;
    @SerializedName("WORKING_WEEK_PRICE")
    private NameValueModel workingWeekPrice;
    @SerializedName("NIGHT_PRICE")
    private NameValueModel nightPrice;
    @SerializedName("ONE_DAY_STOCK_PRICE")
    private NameValueModel oneDayStockPrice;
    @SerializedName("FIFTH_DAY_STOCK_PRICE")
    private NameValueModel fifteenDayStockPrice;
    @SerializedName("TWO_FIVE_DAY_STOCK_PRICE")
    private NameValueModel twoFiveDayStockPrice;
    @SerializedName("THREE_FIFTH_DAY_STOCK_PRICE")
    private NameValueModel threeFifteenDayStockPrice;
    @SerializedName("FIFTH_THIRTY_DAY_STOCK_PRICE")
    private NameValueModel fifteenThirtyDayStockPrice;
    @SerializedName("DAY_OFF_STOCK_PRICE")
    private NameValueModel dayOffStockPrice;
    @SerializedName("WORKING_WEEK_STOCK_PRICE")
    private NameValueModel workingStockPrice;
    @SerializedName("NIGHT_STOCK_PRICE")
    private NameValueModel nightStockPrice;
    @SerializedName("VIDEO_YOUTUBE")
    private NameValueModel youtubeVideo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NameValueModel getYear() {
        return year;
    }

    public void setYear(NameValueModel year) {
        this.year = year;
    }

    public NameValueModel getEngine() {
        return engine;
    }

    public void setEngine(NameValueModel engine) {
        this.engine = engine;
    }

    public NameValuesModel getColor() {
        return color;
    }

    public void setColor(NameValuesModel color) {
        this.color = color;
    }

    public NameValueModel getEquipment() {
        return equipment;
    }

    public void setEquipment(NameValueModel equipment) {
        this.equipment = equipment;
    }

    public NameValueModel getSuspension() {
        return suspension;
    }

    public void setSuspension(NameValueModel suspension) {
        this.suspension = suspension;
    }

    public NameValueModel getSalon() {
        return salon;
    }

    public void setSalon(NameValueModel salon) {
        this.salon = salon;
    }

    public NameValueModel getKpp() {
        return kpp;
    }

    public void setKpp(NameValueModel kpp) {
        this.kpp = kpp;
    }

    public NameValueModel getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(NameValueModel driveUnit) {
        this.driveUnit = driveUnit;
    }

    public NameValueModel getFuel() {
        return fuel;
    }

    public void setFuel(NameValueModel fuel) {
        this.fuel = fuel;
    }

    public NameValueModel getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(NameValueModel placeNumber) {
        this.placeNumber = placeNumber;
    }

    public NameValuesModel getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(NameValuesModel entertainment) {
        this.entertainment = entertainment;
    }

    public NameValueModel getAbs() {
        return abs;
    }

    public void setAbs(NameValueModel abs) {
        this.abs = abs;
    }

    public NameValueModel getConditioning() {
        return conditioning;
    }

    public void setConditioning(NameValueModel conditioning) {
        this.conditioning = conditioning;
    }

    public NameValueModel getPowerSteering() {
        return powerSteering;
    }

    public void setPowerSteering(NameValueModel powerSteering) {
        this.powerSteering = powerSteering;
    }

    public NameValuesModel getAssistance() {
        return assistance;
    }

    public void setAssistance(NameValuesModel assistance) {
        this.assistance = assistance;
    }

    public NameValueModel getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(NameValueModel minExperience) {
        this.minExperience = minExperience;
    }

    public NameValueModel getMinAge() {
        return minAge;
    }

    public void setMinAge(NameValueModel minAge) {
        this.minAge = minAge;
    }

    public NameValueModel getDeposit() {
        return deposit;
    }

    public void setDeposit(NameValueModel deposit) {
        this.deposit = deposit;
    }

    public NameValueModel getMileage() {
        return mileage;
    }

    public void setMileage(NameValueModel mileage) {
        this.mileage = mileage;
    }

    public NameValueModel getRentTerms() {
        return rentTerms;
    }

    public void setRentTerms(NameValueModel rentTerms) {
        this.rentTerms = rentTerms;
    }

    public NameValueModel getTariffRules() {
        return tariffRules;
    }

    public void setTariffRules(NameValueModel tariffRules) {
        this.tariffRules = tariffRules;
    }

    public NameValueModel getOneDayPrice() {
        return oneDayPrice;
    }

    public void setOneDayPrice(NameValueModel oneDayPrice) {
        this.oneDayPrice = oneDayPrice;
    }

    public NameValuesModel getAdditionalPhotos() {
        return additionalPhotos;
    }

    public void setAdditionalPhotos(NameValuesModel additionalPhotos) {
        this.additionalPhotos = additionalPhotos;
    }

    public NameValueModel getSuv() {
        return suv;
    }

    public void setSuv(NameValueModel suv) {
        this.suv = suv;
    }

    public NameValueModel getNovelty() {
        return novelty;
    }

    public void setNovelty(NameValueModel novelty) {
        this.novelty = novelty;
    }

    public NameValueModel getStock() {
        return stock;
    }

    public void setStock(NameValueModel stock) {
        this.stock = stock;
    }

    public NameValueModel getDelivery() {
        return delivery;
    }

    public void setDelivery(NameValueModel delivery) {
        this.delivery = delivery;
    }

    public NameValueModel getUnlimitedTariff() {
        return unlimitedTariff;
    }

    public void setUnlimitedTariff(NameValueModel unlimitedTariff) {
        this.unlimitedTariff = unlimitedTariff;
    }

    public NameValueModel getRemoveFranchise() {
        return removeFranchise;
    }

    public void setRemoveFranchise(NameValueModel removeFranchise) {
        this.removeFranchise = removeFranchise;
    }

    public NameValueModel getWashing() {
        return washing;
    }

    public void setWashing(NameValueModel washing) {
        this.washing = washing;
    }

    public NameValueModel getRefund() {
        return refund;
    }

    public void setRefund(NameValueModel refund) {
        this.refund = refund;
    }

    public NameValueModel getAppMobile() {
        return appMobile;
    }

    public void setAppMobile(NameValueModel appMobile) {
        this.appMobile = appMobile;
    }

    public NameValueModel getTwoFiveDayPrice() {
        return twoFiveDayPrice;
    }

    public void setTwoFiveDayPrice(NameValueModel twoFiveDayPrice) {
        this.twoFiveDayPrice = twoFiveDayPrice;
    }

    public NameValueModel getThreeFifteenDayPrice() {
        return threeFifteenDayPrice;
    }

    public void setThreeFifteenDayPrice(NameValueModel threeFifteenDayPrice) {
        this.threeFifteenDayPrice = threeFifteenDayPrice;
    }

    public NameValueModel getFifteenThirtyDayPrice() {
        return fifteenThirtyDayPrice;
    }

    public void setFifteenThirtyDayPrice(NameValueModel fifteenThirtyDayPrice) {
        this.fifteenThirtyDayPrice = fifteenThirtyDayPrice;
    }

    public NameValueModel getFifteenDayPrice() {
        return fifteenDayPrice;
    }

    public void setFifteenDayPrice(NameValueModel fifteenDayPrice) {
        this.fifteenDayPrice = fifteenDayPrice;
    }

    public NameValueModel getDayOffPrice() {
        return dayOffPrice;
    }

    public void setDayOffPrice(NameValueModel dayOffPrice) {
        this.dayOffPrice = dayOffPrice;
    }

    public NameValueModel getWorkingWeekPrice() {
        return workingWeekPrice;
    }

    public void setWorkingWeekPrice(NameValueModel workingWeekPrice) {
        this.workingWeekPrice = workingWeekPrice;
    }

    public NameValueModel getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(NameValueModel nightPrice) {
        this.nightPrice = nightPrice;
    }

    public NameValueModel getOneDayStockPrice() {
        return oneDayStockPrice;
    }

    public void setOneDayStockPrice(NameValueModel oneDayStockPrice) {
        this.oneDayStockPrice = oneDayStockPrice;
    }

    public NameValueModel getFifteenDayStockPrice() {
        return fifteenDayStockPrice;
    }

    public void setFifteenDayStockPrice(NameValueModel fifteenDayStockPrice) {
        this.fifteenDayStockPrice = fifteenDayStockPrice;
    }

    public NameValueModel getTwoFiveDayStockPrice() {
        return twoFiveDayStockPrice;
    }

    public void setTwoFiveDayStockPrice(NameValueModel twoFiveDayStockPrice) {
        this.twoFiveDayStockPrice = twoFiveDayStockPrice;
    }

    public NameValueModel getThreeFifteenDayStockPrice() {
        return threeFifteenDayStockPrice;
    }

    public void setThreeFifteenDayStockPrice(NameValueModel threeFifteenDayStockPrice) {
        this.threeFifteenDayStockPrice = threeFifteenDayStockPrice;
    }

    public NameValueModel getFifteenThirtyDayStockPrice() {
        return fifteenThirtyDayStockPrice;
    }

    public void setFifteenThirtyDayStockPrice(NameValueModel fifteenThirtyDayStockPrice) {
        this.fifteenThirtyDayStockPrice = fifteenThirtyDayStockPrice;
    }

    public NameValueModel getDayOffStockPrice() {
        return dayOffStockPrice;
    }

    public void setDayOffStockPrice(NameValueModel dayOffStockPrice) {
        this.dayOffStockPrice = dayOffStockPrice;
    }

    public NameValueModel getWorkingStockPrice() {
        return workingStockPrice;
    }

    public void setWorkingStockPrice(NameValueModel workingStockPrice) {
        this.workingStockPrice = workingStockPrice;
    }

    public NameValueModel getNightStockPrice() {
        return nightStockPrice;
    }

    public void setNightStockPrice(NameValueModel nightStockPrice) {
        this.nightStockPrice = nightStockPrice;
    }

    public NameValueModel getYoutubeVideo() {
        return youtubeVideo;
    }

    public void setYoutubeVideo(NameValueModel youtubeVideo) {
        this.youtubeVideo = youtubeVideo;
    }

    @Override
    public String toString() {
        return "AutoModel{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", url='" + url + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", text='" + text + '\'' +
                ", year=" + year +
                ", engine=" + engine +
                ", color=" + color +
                ", equipment=" + equipment +
                ", suspension=" + suspension +
                ", salon=" + salon +
                ", kpp=" + kpp +
                ", driveUnit=" + driveUnit +
                ", fuel=" + fuel +
                ", placeNumber=" + placeNumber +
                ", entertainment=" + entertainment +
                ", abs=" + abs +
                ", conditioning=" + conditioning +
                ", powerSteering=" + powerSteering +
                ", assistance=" + assistance +
                ", minExperience=" + minExperience +
                ", minAge=" + minAge +
                ", deposit=" + deposit +
                ", mileage=" + mileage +
                ", rentTerms=" + rentTerms +
                ", tariffRules=" + tariffRules +
                ", oneDayPrice=" + oneDayPrice +
                ", additionalPhotos=" + additionalPhotos +
                ", suv=" + suv +
                ", novelty=" + novelty +
                ", stock=" + stock +
                ", delivery=" + delivery +
                ", unlimitedTariff=" + unlimitedTariff +
                ", removeFranchise=" + removeFranchise +
                ", washing=" + washing +
                ", refund=" + refund +
                ", appMobile=" + appMobile +
                ", twoFiveDayPrice=" + twoFiveDayPrice +
                ", threeFifteenDayPrice=" + threeFifteenDayPrice +
                ", fifteenThirtyDayPrice=" + fifteenThirtyDayPrice +
                ", fifteenDayPrice=" + fifteenDayPrice +
                ", dayOffPrice=" + dayOffPrice +
                ", workingWeekPrice=" + workingWeekPrice +
                ", nightPrice=" + nightPrice +
                ", oneDayStockPrice=" + oneDayStockPrice +
                ", fifteenDayStockPrice=" + fifteenDayStockPrice +
                ", twoFiveDayStockPrice=" + twoFiveDayStockPrice +
                ", threeFifteenDayStockPrice=" + threeFifteenDayStockPrice +
                ", fifteenThirtyDayStockPrice=" + fifteenThirtyDayStockPrice +
                ", dayOffStockPrice=" + dayOffStockPrice +
                ", workingStockPrice=" + workingStockPrice +
                ", nightStockPrice=" + nightStockPrice +
                ", youtubeVideo=" + youtubeVideo +
                '}';
    }

    public static NameValueModel toValue(NameValuesModel nameValuesModel) {
        StringBuilder result = new StringBuilder("");
        String name = "";
        NameValueModel nameValueModel = new NameValueModel();
        if (nameValuesModel != null){
            name = nameValuesModel.getName();
            for (String value : nameValuesModel.getValues()) {
                result.append(value);
            }
        }

        nameValueModel.setName(name);
        nameValueModel.setValue(result.toString());
        return nameValueModel;
    }


    public static NameValueModel appendValuta( NameValueModel nameValueModel) {
        NameValueModel result = new NameValueModel();
        result.setName(nameValueModel.getName());
        result.setValue(nameValueModel.getValue() + " " + App.getInstance().getString(R.string.valutaRu));
        return result;
    }

}
