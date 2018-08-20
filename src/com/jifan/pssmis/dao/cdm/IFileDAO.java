package com.jifan.pssmis.dao.cdm;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.cdm.FileBO;
import com.jifan.pssmis.model.vo.cdm.FileQueryVO;
public interface IFileDAO extends IBaseDAO<FileBO,String>{
        public List<FileBO> findFileByParam(FileQueryVO param);

}
