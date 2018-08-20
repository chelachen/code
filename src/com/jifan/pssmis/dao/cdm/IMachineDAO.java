package com.jifan.pssmis.dao.cdm;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.cdm.MachineBO;
import com.jifan.pssmis.model.vo.cdm.MachineQueryVO;
public interface IMachineDAO extends IBaseDAO<MachineBO,String>{
        public List<MachineBO> findMachineByParam(MachineQueryVO param);

}
