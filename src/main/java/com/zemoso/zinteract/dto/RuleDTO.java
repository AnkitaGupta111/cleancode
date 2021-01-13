package com.zemoso.zinteract.dto;

import com.zemoso.zinteract.model.Header;
import com.zemoso.zinteract.model.Option;
import com.zemoso.zinteract.model.Rows;
import lombok.*;

import java.util.List;

/**
 * The Rule dto, used as Data Transfer Object over the network.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RuleDTO {

    private String name;

    private String description;

    private String artifact_id;

    private List<Option> options;

    private Header headers;

    private List<Rows> rows;

}
