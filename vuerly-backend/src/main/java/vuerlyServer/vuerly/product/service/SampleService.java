package vuerlyServer.vuerly.product.service;

import vuerlyServer.vuerly.product.model.SampleDTO;

import java.util.List;

public interface SampleService {
    public List<SampleDTO> selectSample();

    public List<SampleDTO> selectSampleParam(SampleDTO sampleDTO);
}
