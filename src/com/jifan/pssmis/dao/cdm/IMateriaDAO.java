package com.jifan.pssmis.dao.cdm;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.cdm.MateriaBO;
import com.jifan.pssmis.model.vo.cdm.MateriaQueryVO;
public interface IMateriaDAO extends IBaseDAO<MateriaBO,String>{
        public List<MateriaBO> findMateriaByParam(MateriaQueryVO param);
        public List<MateriaBO> findMateriaByParamEqual(MateriaQueryVO param);

}
