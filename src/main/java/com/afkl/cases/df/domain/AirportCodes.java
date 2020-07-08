package com.afkl.cases.df.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"_embedded",
"page"
})
public class AirportCodes {

@JsonProperty("_embedded")
private Locations embedded;
@JsonProperty("page")
private Page page;


@JsonProperty("_embedded")
public Locations getEmbedded() {
return embedded;
}

@JsonProperty("_embedded")
public void setEmbedded(Locations embedded) {
this.embedded = embedded;
}

@JsonProperty("page")
public Page getPage() {
return page;
}

@JsonProperty("page")
public void setPage(Page page) {
this.page = page;
}


}