package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

public enum Format {

    DVD(480),
    BluRay(1080),
    UHD_4K(2160),
    VHS(0),
    Laser_Disc(0),
    HDDVD(0),
    Betamax(0);

    private final int maxResolution;

    Format(int maxResolution) {
        this.maxResolution = maxResolution;
    }

    public boolean isHighDefinition() {
        return maxResolution >= 1080;
    }

    public int maxResolution() {
        return maxResolution;
    }

}
