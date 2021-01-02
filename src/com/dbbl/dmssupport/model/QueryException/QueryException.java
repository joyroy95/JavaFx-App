package com.dbbl.dmssupport.model.QueryException;

import com.dbbl.dmssupport.common.vo.ListVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryException {

    private Connection connection;

    public QueryException(Connection connection) {
        this.connection = connection;
    }

    public String fetchCurrentStatus(String internalReferenceNo) throws SQLException {
        String sqlQuery = "select account_balance from account where account_no = :p_internalReferenceNo";
        PreparedStatement s=connection.prepareStatement(sqlQuery);      //creating statement
        s.setString(1, internalReferenceNo);
        ResultSet rs=s.executeQuery();   //executing statement

        String store = "";
        if(rs.getFetchSize()>0)
        {
            while(rs.next()){
               store  = rs.getString("account_balance");
                System.out.println(rs.getString(1));
            }
        }

        return store;

    }

    public List<ListVo> loadStatusForDropDown() throws SQLException {
       List<ListVo> loadstatus = new ArrayList<>();
       ListVo listVo = null;
        String sqlQuery = "select a.role_id, a.role_id || '-' || a.role_name as role_name from app_role a";
        PreparedStatement s=connection.prepareStatement(sqlQuery);      //creating statement
        ResultSet rs=s.executeQuery();   //executing statement
        if(rs.getFetchSize()>0)
        {
            while(rs.next()){
                listVo = new ListVo();
                listVo.setListKey(rs.getString(1));
                listVo.setListValue(rs.getString(2));
                loadstatus.add(listVo);
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }
        }
        return loadstatus;
    }
}
