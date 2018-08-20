package com.jifan.pssmis.bs.cdm;
import java.util.List;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.model.bo.cdm.MachineBO;
import com.jifan.pssmis.model.vo.cdm.MachineQueryVO;
public interface IMachineBS extends IBaseBS<MachineBO,String>  {
        public List<MachineBO> findMachineByParam(MachineQueryVO param);
        public String saveNotExist(MachineBO entity) throws BizException;
        public void updateNotExist(MachineBO entity) throws BizException;

}
