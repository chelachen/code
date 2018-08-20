package com.jifan.pssmis.bs.cdm;
import java.util.List;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.model.bo.cdm.FileBO;
import com.jifan.pssmis.model.vo.cdm.FileQueryVO;
public interface IFileBS extends IBaseBS<FileBO,String>  {
        public List<FileBO> findFileByParam(FileQueryVO param);
        public String saveNotExist(FileBO entity) throws BizException;

}
