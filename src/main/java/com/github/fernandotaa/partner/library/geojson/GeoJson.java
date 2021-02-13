package com.github.fernandotaa.partner.library.geojson;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Abstraction of {@link GeoJson} object.
 * For more information: https://en.wikipedia.org/wiki/GeoJSON
 */
@Getter
@AllArgsConstructor
public abstract class GeoJson {
    private String type;
}
