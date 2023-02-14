package vuerlyServer.vuerly.product.repository;

import org.apache.ibatis.annotations.Mapper;
import vuerlyServer.vuerly.product.model.SampleDTO;


import java.util.List;

@Mapper
public interface SampleDAO {

    List<SampleDTO> selectSampleDAO();
}
