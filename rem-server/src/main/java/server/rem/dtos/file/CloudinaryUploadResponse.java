package server.rem.dtos.file;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudinaryUploadResponse {
    @JsonProperty("original_filename")
    private final String originalFilename;

    @JsonProperty("display_name")
    private final String displayName;
    
    @JsonProperty("asset_folder")
    private final String assetFolder;
    
    @JsonProperty("url")
    private final String url;

    @JsonProperty("placeholder")
    private final String placeholder;
    
    @JsonProperty("etag")
    private final String etag;
    
    @JsonProperty("type")
    private final String type;

    @JsonProperty("bytes")
    private final Integer bytes;

    @JsonProperty("tags")
    private final List<String> tags;

    @JsonProperty("created_at")
    private final String created_at;
    
    @JsonProperty("format")
    private final String format;

    @JsonProperty("height")
    private final String height;
    
    @JsonProperty("width")
    private final String width;

    @JsonProperty("signature")
    private final String signature;

    @JsonProperty("version_id")
    private final String versionId;

    @JsonProperty("asset_id")
    private final String assetId;

    @JsonProperty("version")
    private final String version;

    @JsonProperty("secure_url")
    private final String secureUrl;

    @JsonProperty("public_id")
    private final String publicId;

    @JsonProperty("resource_type")
    private final String resourceType;
}