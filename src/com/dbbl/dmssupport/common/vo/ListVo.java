package com.dbbl.dmssupport.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.StringJoiner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListVo {
    private String listKey;
    private String listValue;

   /* @Override
    public String toString() {
        return this.listValue;
    }*/
}
