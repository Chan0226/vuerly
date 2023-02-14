package vuerlyServer.vuerly.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vuerlyServer.vuerly.product.model.SampleDTO;
import vuerlyServer.vuerly.product.repository.SampleDAO;

import java.util.List;

@Slf4j
@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleDAO sampleDAO;

    @Override
    public List<SampleDTO> selectSample() {
        List<SampleDTO> list = sampleDAO.selectSampleDAO();
        return list;
    }

    @Override
    public List<SampleDTO> selectSampleParam(SampleDTO sampleDTO) {
        return sampleDAO.selectSampleDAO();
    }
}
