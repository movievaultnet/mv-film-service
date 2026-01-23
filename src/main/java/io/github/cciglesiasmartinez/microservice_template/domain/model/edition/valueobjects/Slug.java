package io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.ReleaseYear;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.time.Year;
import java.util.Base64;
import java.util.Locale;

public class Slug {

    private String value;

    @JsonCreator
    private Slug(@JsonProperty("value") String value) {
        this.value = value;
    }

    public static Slug of(String value) {
        if ((value == null) || value.isBlank()) {
            throw new IllegalArgumentException("Slug can't be empty.");
        }
        return new Slug(value);
    }

    private static String getShortHash(String name) throws NoSuchAlgorithmException {
        long timestamp = System.currentTimeMillis();
        String input = name + timestamp;
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(input.getBytes());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(new byte[]{hash[0], hash[1], hash[2], hash[3]});
    }

    private static String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private static String slugify(String name) {
        if (name == null )
            return "";
        String slug = normalize(name)
//                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-zA-Z0-9]+", "-")
                .replaceAll("^-+|-+$", "")
                .replaceAll("-{2,}", "-");
        return slug;
    }

    public static Slug generate(String name, ReleaseYear year) {
        String shortHash;
        try {
            shortHash = Slug.getShortHash(name);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        String slugifiedName = slugify(name);
        return Slug.of(String.format("%s-%d-%s", slugifiedName, year.value(), shortHash));
    }

    public String value() {
        return this.value;
    }
}
