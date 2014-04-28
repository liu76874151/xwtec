package com.xwtech.uomp.base.service.sequence.impl;

import com.xwtech.uomp.base.dao.sequence.ISequenceDAO;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-29
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
@Service("sequenceService")
public class SequenceServiceImpl implements ISequenceService {
    @Autowired
    ISequenceDAO sequenceDAO;

    public String getSequence(String sequenceName) {
        return sequenceDAO.getSequence(sequenceName);
    }
}
