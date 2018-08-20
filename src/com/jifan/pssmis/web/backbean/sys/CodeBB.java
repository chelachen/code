package com.jifan.pssmis.web.backbean.sys;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Repository;

import com.jifan.pssmis.bs.sys.ICodeBS;
import com.jifan.pssmis.bs.sys.ICodeDtlBS;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.sys.CodeBO;
import com.jifan.pssmis.model.bo.sys.CodeDtlBO;
import com.jifan.pssmis.model.vo.sys.CodeDtlQueryVO;
import com.jifan.pssmis.model.vo.sys.CodeQueryVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;

@ManagedBean(name = "codeBB")
public class CodeBB extends BaseBean {
	/**
        *
        **/
	private static final long serialVersionUID = -1054820662761921269L;

	private CodeQueryVO param = new CodeQueryVO();

	private CodeBO currentBO = new CodeBO();
	private CodeDtlBO dtlBO = new CodeDtlBO();

	private List<CodeBO> resultList = new ArrayList<CodeBO>();
	private List<CodeDtlBO> dtlList = new ArrayList<CodeDtlBO>();

	private String defaultBagName = "com.jifan.pssmis";

	@ManagedProperty(name = "codeBS", value = "#{codeBS}")
	private ICodeBS codeBS;
	@ManagedProperty(name = "codeDtlBS", value = "#{codeDtlBS}")
	private ICodeDtlBS codeDtlBS;

	private String boStr = "";
	private String voStr = "";
	private String idaoStr = "";
	private String daoStr = "";
	private String ibsStr = "";
	private String bsStr = "";
	private String iuccStr = "";
	private String uccStr = "";
	private String bbStr = "";
	private String bbUccStr = "";
	private String pfStr = "";
	private String rfStr = "";

	public CodeBB() {
		pager.setPageNumber(1);
		pager.setPageSize(10);
		currentBO = new CodeBO();
		
	}
	
	@PostConstruct
	public void init(){
		this.findByPager();
	}

	public void findByPager() {
		try {
			this.resultList = this.codeBS.findCodeByParam(param);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * 根据属性名转换sql字段名
	 */
	public void changeDtlList() {
		try {
			for (CodeDtlBO dtl : this.dtlList) {
				dtl.changeFildNameToSqlName();
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	/**
	 * 根据类名转换表名
	 */
	public void changeCodeToTable() {
		try {
			this.currentBO.changeFullTableName();
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	/**
	 * 生成代码
	 */
	public void codeBuild() {
		try {
			if (this.currentBO != null) {
				if (CommonUtil.isEmpty(this.currentBO.getId()))
					this.save();
				this.buildBOStr();
				this.buildVOStr();
				this.buildIDAOStr();
				this.buildDAOStr();
				this.buildIBSStr();
				this.buildBSStr();
				this.buildIUCCStr();
				this.buildUCCStr();
				this.buildBBStr();
				buildBBUccStr();
				this.buildRFStr();
				this.buildPFSStr();
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	private void buildBOStr() {
		this.boStr = "package "
				+ this.currentBO.getBagName()
				+ ".model.bo."
				+ this.currentBO.getMdName()
				+ ";\r\n"
				+ "import java.util.Date;\r\n"
				+ "import java.math.BigDecimal;\r\n"
				+ "import javax.persistence.Column;\r\n"
				+ "import javax.persistence.Entity;\r\n"
				+ "import javax.persistence.Table;\r\n"
				+ "import org.hibernate.annotations.OptimisticLockType;\r\n"
				+ "import com.jifan.pssmis.model.bo.base.AuditableBO;\r\n"
				+ "@Entity\r\n"
				+ "@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)\r\n"
				+ "@Table(name = \"" + this.currentBO.getTableName()
				+ "\")\r\n" + "public class " + this.currentBO.getClassName()
				+ "BO extends AuditableBO {\r\n"
				+ "	private static final long serialVersionUID = -1L;\r\n\r\n";
		// 属性
		for (CodeDtlBO dtl : this.dtlList) {
			// 字段名
			this.boStr = this.boStr + "	@Column(name = \"" + dtl.getSqlName()
					+ "\"";
			// 字段长度
			if (dtl.getFieldType().equals("String")) {
				this.boStr = this.boStr + ", columnDefinition=\" varchar("
						+ dtl.getLength() + ")\"";
			}
			if (dtl.getFieldType().equals("Integer"))
				this.boStr = this.boStr + ", length=" + dtl.getLength();
			if (dtl.getFieldType().equals("BigDecimal"))
				this.boStr = this.boStr + ", precision=" + dtl.getLength()
						+ ", scale = 5";

			// 是否可空
			if (dtl.getCanNull().equals("0"))
				this.boStr = this.boStr + ",nullable = false";
			// 默认值
			// if (CommonUtil.isNotEmpty(dtl.getDefaultValue()))
			// this.boStr = this.boStr + ",default = " + dtl.getDefaultValue();
			this.boStr = this.boStr + ")\r\n";
			this.boStr = this.boStr + "	private " + dtl.getFieldType() + " "
					+ dtl.getFieldNameLow() + "; //" + dtl.getMemo()
					+ "\r\n\r\n";
		}

		// 构造函数
		this.boStr = this.boStr + "	public " + this.currentBO.getClassName()
				+ "BO(){\r\n	}\r\n";
		// 构造函数-带参数
		this.boStr = this.boStr + "	public " + this.currentBO.getClassName()
				+ "BO(";
		String fieldName = "";
		// 参数
		for (CodeDtlBO dtl : this.dtlList) {
			if (CommonUtil.isEmpty(fieldName))
				fieldName = dtl.getFieldType() + " " + dtl.getFieldNameLow();
			else
				fieldName = fieldName + "," + dtl.getFieldType() + " "
						+ dtl.getFieldNameLow();
		}
		this.boStr = this.boStr + fieldName;
		this.boStr = this.boStr + "){\r\n";
		// 赋值
		for (CodeDtlBO dtl : this.dtlList) {
			this.boStr = this.boStr + "		this." + dtl.getFieldNameLow() + "="
					+ dtl.getFieldNameLow() + ";\r\n";
		}
		this.boStr = this.boStr + "	}\r\n";

		// get,set方法
		for (CodeDtlBO dtl : this.dtlList) {
			// get
			this.boStr = this.boStr + "	 public " + dtl.getFieldType() + " get"
					+ dtl.getFieldName() + "(){\r\n" + "			return  this."
					+ dtl.getFieldNameLow() + ";\r\n	}\r\n";
			// set
			this.boStr = this.boStr + "	 public void set" + dtl.getFieldName()
					+ "(" + dtl.getFieldType() + " " + dtl.getFieldNameLow()
					+ "){\r\n" + "			 this." + dtl.getFieldNameLow() + " = "
					+ dtl.getFieldNameLow() + ";\r\n	}\r\n";
		}
		this.boStr = this.boStr + "}";
	}

	private void buildVOStr() {
		this.voStr = "package " + this.currentBO.getBagName() + ".model.vo."
				+ this.currentBO.getMdName() + ";\r\n"
				+ "import java.util.Date;\r\n"
				+ "import java.math.BigDecimal;\r\n"
				+ "import com.jifan.pssmis.model.vo.base.BaseVO;\r\n"
				+ "public class " + this.currentBO.getClassName()
				+ "QueryVO extends BaseVO {\r\n"
				+ "	private static final long serialVersionUID = -1L;\r\n\r\n";
		// 属性
		for (CodeDtlBO dtl : this.dtlList) {
			this.voStr = this.voStr + "	private " + dtl.getFieldType() + " "
					+ dtl.getFieldNameLow() + "; //" + dtl.getMemo()
					+ "\r\n\r\n";
		}

		// 构造函数
		this.voStr = this.voStr + "	public " + this.currentBO.getClassName()
				+ "QueryVO(){\r\n	}\r\n";
		// 构造函数-带参数
		this.voStr = this.voStr + "	public " + this.currentBO.getClassName()
				+ "QueryVO(";
		String fieldName = "";
		// 参数
		for (CodeDtlBO dtl : this.dtlList) {
			if (CommonUtil.isEmpty(fieldName))
				fieldName = dtl.getFieldType() + " " + dtl.getFieldNameLow();
			else
				fieldName = fieldName + "," + dtl.getFieldType() + " "
						+ dtl.getFieldNameLow();
		}
		this.voStr = this.voStr + fieldName;
		this.voStr = this.voStr + "){\r\n";
		// 赋值
		for (CodeDtlBO dtl : this.dtlList) {
			this.voStr = this.voStr + "		this." + dtl.getFieldNameLow() + "="
					+ dtl.getFieldNameLow() + ";\r\n";
		}
		this.voStr = this.voStr + "	}\r\n";

		// get,set方法
		for (CodeDtlBO dtl : this.dtlList) {
			// get
			this.voStr = this.voStr + "	 public " + dtl.getFieldType() + " get"
					+ dtl.getFieldName() + "(){\r\n" + "			return  this."
					+ dtl.getFieldNameLow() + ";\r\n	}\r\n";
			// set
			this.voStr = this.voStr + "	 public void set" + dtl.getFieldName()
					+ "(" + dtl.getFieldType() + " " + dtl.getFieldNameLow()
					+ "){\r\n" + "			 this." + dtl.getFieldNameLow() + " = "
					+ dtl.getFieldNameLow() + ";\r\n	}\r\n";
		}
		this.voStr = this.voStr + "}";
	}

	private void buildIDAOStr() {
		this.idaoStr = "package " + this.currentBO.getBagName() + ".dao."
				+ this.currentBO.getMdName() + ";\r\n"
				+ "import java.util.List;\r\n"
				+ "import com.jifan.pssmis.dao.base.IBaseDAO;\r\n"
				+ "import com.jifan.pssmis.model.bo."+this.currentBO.getMdName()+"."
				+ this.currentBO.getClassName() + "BO;\r\n"
				+ "import com.jifan.pssmis.model.vo."+this.currentBO.getMdName()+"."
				+ this.currentBO.getClassName() + "QueryVO;\r\n"
				+ "public interface I" + this.currentBO.getClassName()
				+ "DAO extends IBaseDAO<" + this.currentBO.getClassName()
				+ "BO,String> {\r\n"

				+ "		public List<" + this.currentBO.getClassName() + "BO> find"
				+ this.currentBO.getClassName() + "ByParam("
				+ this.currentBO.getClassName() + "QueryVO param);\r\n" + "}";
	}

	private void buildDAOStr() {
		this.daoStr = "package "
				+ this.currentBO.getBagName()
				+ ".dao."
				+ this.currentBO.getMdName()
				+ ".impl;\r\n"
				+ "import java.util.List;\r\n"
				+ "import java.util.ArrayList;\r\n"
				+ "import com.jifan.pssmis.foundation.util.CommonUtil;\r\n"
				+ "import org.springframework.stereotype.Repository;\r\n"
				+ "import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;\r\n"
				+ "import com.jifan.pssmis.foundation.util.DAOUtil;\r\n"
				+ "import com.jifan.pssmis.dao."+this.currentBO.getMdName()+".I"+this.currentBO.getClassName()+"DAO;\r\n"
				+ "import com.jifan.pssmis.model.bo."+this.currentBO.getMdName()+"."+this.currentBO.getClassName()+"BO;\r\n"
				+ "import com.jifan.pssmis.model.vo."+this.currentBO.getMdName()+"."+this.currentBO.getClassName()+"QueryVO;\r\n"
				+ "@Repository\r\n"
				+ "public class " + this.currentBO.getClassName()
				+ "DAO extends BaseDAOImpl<"+this.currentBO.getClassName()+"BO,String> implements I"+this.currentBO.getClassName()+"DAO{\r\n"
				
				+ "		public List<"+this.currentBO.getClassName()+"BO> find"+this.currentBO.getClassName()+"ByParam("+this.currentBO.getClassName()+"QueryVO param){\r\n"
				+ "			String hql = \"from \" + "+this.currentBO.getClassName()+"BO.class.getName() + \" where 1=1\";\r\n"
				+ "			List list = new ArrayList();\r\n";
		for (CodeDtlBO dtl : this.dtlList) {
			if (dtl.getFieldType().equals("String")) {
			this.daoStr=this.daoStr
				+ "			if (CommonUtil.isNotEmpty(param.get"+dtl.getFieldName()+"())) {\r\n";
			} else if (dtl.getFieldType().equals("Integer")) {
				this.daoStr=this.daoStr 
				+ "			if (param.get"+dtl.getFieldName()+"() != null && param.get"+dtl.getFieldName()+"().intValue()!=0) {\r\n";
			}
			if (dtl.getFieldType().equals("String") || dtl.getFieldType().equals("Integer")) {	
			this.daoStr=this.daoStr 	
				+ "				hql = hql + \" and "+dtl.getFieldNameLow()+" = ? \";\r\n"
				+ "				list.add(param.get"+dtl.getFieldName()+"());\r\n"
				+ "			}\r\n";
			}
		}
		this.daoStr=this.daoStr 
+ "			Object[] objects = DAOUtil.getObjectsByList(list);\r\n"
+ "			List<"+this.currentBO.getClassName()+"BO> retultList = this.getHibernateTemplate()\r\n"
+ "					.find(hql, objects);\r\n"
+ "			return retultList;\r\n"
+ "		}\r\n}";

	}

	private void buildIBSStr() {
		this.ibsStr =
		"package com.jifan.pssmis.bs."+this.currentBO.getMdName()+";\r\n"
		+"import java.util.List;\r\n"
		+"import com.jifan.pssmis.foundation.exception.BizException;\r\n"
		+"import com.jifan.pssmis.bs.base.IBaseBS;\r\n"
		+"import com.jifan.pssmis.model.bo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "BO;\r\n"
		+"import com.jifan.pssmis.model.vo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "QueryVO;\r\n"
		+"public interface I"+ this.currentBO.getClassName() + "BS extends IBaseBS<"+ this.currentBO.getClassName() + "BO,String>  {\r\n"
		+"        public List<"+ this.currentBO.getClassName() + "BO> find"+ this.currentBO.getClassName() + "ByParam("+ this.currentBO.getClassName() + "QueryVO param);\r\n"
		+"        public String saveNotExist("+ this.currentBO.getClassName() + "BO entity) throws BizException;\r\n"
		+"}";

	}

	private void buildBSStr() {
		this.bsStr =
		"package com.jifan.pssmis.bs."+this.currentBO.getMdName()+".impl;\r\n"
		+"import java.util.List;\r\n"
		+"import javax.annotation.Resource;\r\n"
		+"import org.hibernate.criterion.DetachedCriteria;\r\n"
		+"import org.hibernate.criterion.Disjunction;\r\n"
		+"import org.hibernate.criterion.Restrictions;\r\n"
		+"import org.springframework.stereotype.Service;\r\n"
		+"import com.jifan.pssmis.bs.base.impl.BaseBSImpl;\r\n"
		+"import com.jifan.pssmis.bs."+this.currentBO.getMdName()+".I"+ this.currentBO.getClassName() + "BS;\r\n"
		+"import com.jifan.pssmis.dao."+this.currentBO.getMdName()+".I"+ this.currentBO.getClassName() + "DAO;\r\n"
		+"import com.jifan.pssmis.foundation.exception.BizException;\r\n"
		+"import com.jifan.pssmis.foundation.util.CommonUtil;\r\n"
		+"import com.jifan.pssmis.model.bo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "BO;\r\n"
		+"import com.jifan.pssmis.model.vo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "QueryVO;\r\n"
		+"@Service(\""+ this.currentBO.getClassNameLow() + "BS\")\r\n"
		+"public class "+ this.currentBO.getClassName() + "BSImpl extends BaseBSImpl<"+ this.currentBO.getClassName() + "BO,String> implements I"+ this.currentBO.getClassName() + "BS{\r\n"
		+"        @Resource\r\n"
		+"        private I"+ this.currentBO.getClassName() + "DAO "+ this.currentBO.getClassNameLow() + "DAO;\r\n\r\n"
		+"        @Resource\r\n"
		+"        public void setBaseDAO(I"+ this.currentBO.getClassName() + "DAO "+ this.currentBO.getClassNameLow() + "DAO) {\r\n"
		+"                super.setBaseDAO("+ this.currentBO.getClassNameLow() + "DAO);\r\n"
		+"        }\r\n"
		+"        public List<"+ this.currentBO.getClassName() + "BO> find"+ this.currentBO.getClassName() + "ByParam("+ this.currentBO.getClassName() + "QueryVO param) {\r\n"
		+"                return this."+ this.currentBO.getClassNameLow() + "DAO.find"+ this.currentBO.getClassName() + "ByParam(param);\r\n"
		+"        }\r\n"
		+"        public String saveNotExist("+ this.currentBO.getClassName() + "BO entity) throws BizException{\r\n"
		+"                DetachedCriteria detachedCriteria = DetachedCriteria.forClass("+ this.currentBO.getClassName() + "BO.class);\r\n"
		+"                Disjunction dis = Restrictions.disjunction();\r\n"
		+"                if(CommonUtil.isNotEmpty(entity.getCode())){\r\n"
		+"                        dis.add(Restrictions.eq(\"code\",entity.getCode()));\r\n"
		+"                }\r\n"
		+"                detachedCriteria.add(dis);\r\n"
		+"                if(CommonUtil.isNotEmpty(entity.getId()))\r\n"
		+"                	detachedCriteria.add(Restrictions.ne(\"id\", entity.getId()));\r\n"
		+"                List list = super.findByDetachedCriteria(detachedCriteria);\r\n"
		+"                if (list.size()>0)\r\n"
		+"                        throw new BizException(\"编码:\"+ entity.getCode() +\"不能重复\");\r\n"
		+"                if(CommonUtil.isEmpty(entity.getId()))\r\n"
		+"                	 super.save(entity);\r\n"
		+"                else\r\n"
		+"                	 super.update(entity);\r\n"
		+"                return entity.getId();\r\n"
		+"        }\r\n\r\n"
		+"        public I"+ this.currentBO.getClassName() + "DAO get"+ this.currentBO.getClassName() + "DAO() {\r\n"
		+"                return "+ this.currentBO.getClassNameLow() + "DAO;\r\n"
		+"       }\r\n\r\n"

		+"       public void set"+ this.currentBO.getClassName() + "DAO(I"+ this.currentBO.getClassName() + "DAO "+ this.currentBO.getClassNameLow() + "DAO) {\r\n"
		+"                this."+ this.currentBO.getClassNameLow() + "DAO = "+ this.currentBO.getClassNameLow() + "DAO;\r\n"
		+"        }\r\n"
		+"}";

	}

	private void buildIUCCStr() {
		this.iuccStr =
		"package com.jifan.pssmis.ucc."+this.currentBO.getMdName()+";\r\n"
		+"import java.util.List;\r\n"
		+"import org.hibernate.criterion.DetachedCriteria;\r\n"
		+"import com.jifan.pssmis.foundation.exception.BizException;\r\n"
		+"import com.jifan.pssmis.foundation.paging.Pager;\r\n"
		+"import com.jifan.pssmis.foundation.paging.Paginator;\r\n"
		+"import com.jifan.pssmis.model.bo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "BO;\r\n"
		+"import com.jifan.pssmis.model.vo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "QueryVO;\r\n"
		+"import com.jifan.pssmis.ucc.base.IUCC;\r\n"
		+"public interface I"+ this.currentBO.getClassName() + "UCC extends IUCC{\r\n"
		+"        public List<"+ this.currentBO.getClassName() + "BO> find"+ this.currentBO.getClassName() + "ByParam("+ this.currentBO.getClassName() + "QueryVO param);\r\n"
		+"        public void delete"+ this.currentBO.getClassName() + "("+ this.currentBO.getClassName() + "BO param)  throws Exception;\r\n"
		+"        public void save"+ this.currentBO.getClassName() + "("+ this.currentBO.getClassName() + "BO param)  throws BizException;\r\n"
		+"        public void update"+ this.currentBO.getClassName() + "("+ this.currentBO.getClassName() + "BO param);\r\n"
		+"        public Paginator findByPager(Paginator pager,final DetachedCriteria detachedCriteria);\r\n\r\n"
		+"}";

	}

	private void buildUCCStr() {
		this.uccStr = 
		"package com.jifan.pssmis.ucc."+this.currentBO.getMdName()+".impl;\r\n"
		+"import java.util.List;\r\n"
		+"import javax.annotation.Resource;\r\n"
		+"import org.hibernate.criterion.DetachedCriteria;\r\n"
		+"import org.springframework.stereotype.Component;\r\n"
		+"import com.jifan.pssmis.foundation.exception.BizException;\r\n"
		+"import com.jifan.pssmis.foundation.paging.Pager;\r\n"
		+"import com.jifan.pssmis.foundation.paging.Paginator;\r\n"
		+"import com.jifan.pssmis.bs."+this.currentBO.getMdName()+".I"+ this.currentBO.getClassName() + "BS;\r\n"
		+"import com.jifan.pssmis.model.bo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "BO;\r\n"
		+"import com.jifan.pssmis.model.vo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "QueryVO;\r\n"
		+"import com.jifan.pssmis.foundation.exception.BizException;\r\n"
		+"import com.jifan.pssmis.ucc."+this.currentBO.getMdName()+".I"+ this.currentBO.getClassName() + "UCC;\r\n"
		+"@Component(\""+ this.currentBO.getClassNameLow() + "UCC\")\r\n"
		+"public class "+ this.currentBO.getClassName() + "UCCImpl implements I"+ this.currentBO.getClassName() + "UCC{\r\n"
		+"        @Resource\r\n"
		+"       private I"+ this.currentBO.getClassName() + "BS "+ this.currentBO.getClassNameLow() + "BS;\r\n"
		+"        public void delete"+ this.currentBO.getClassName() + "("+ this.currentBO.getClassName() + "BO param)  throws Exception{\r\n"
		+"                this."+ this.currentBO.getClassNameLow() + "BS.delete(param);\r\n"
		+"        }\r\n"
		+"        public List<"+ this.currentBO.getClassName() + "BO> find"+ this.currentBO.getClassName() + "ByParam("+ this.currentBO.getClassName() + "QueryVO param) {\r\n"
		+"                return this."+ this.currentBO.getClassNameLow() + "BS.find"+ this.currentBO.getClassName() + "ByParam(param);\r\n"
		+"       }\r\n"
		+"        public void save"+ this.currentBO.getClassName() + "("+ this.currentBO.getClassName() + "BO param) throws BizException {\r\n"
		+"                this."+ this.currentBO.getClassNameLow() + "BS.saveNotExist(param);\r\n"
		+"        }\r\n"
		+"        public void update"+ this.currentBO.getClassName() + "("+ this.currentBO.getClassName() + "BO param) {\r\n"
		+"                this."+ this.currentBO.getClassNameLow() + "BS.update(param);\r\n"
		+"        }\r\n"
		+"        public I"+ this.currentBO.getClassName() + "BS get"+ this.currentBO.getClassName() + "BS() {\r\n"
		+"                return "+ this.currentBO.getClassNameLow() + "BS;\r\n"
		+"        }\r\n"
		+"        public void set"+ this.currentBO.getClassName() + "BS(I"+ this.currentBO.getClassName() + "BS "+ this.currentBO.getClassNameLow() + "BS) {\r\n"
		+"                this."+ this.currentBO.getClassNameLow() + "BS = "+ this.currentBO.getClassNameLow() + "BS;\r\n"
		+"        }\r\n"
		+"        @Override\r\n"
		+"        public Paginator findByPager(Paginator paginator,final DetachedCriteria detachedCriteria) {\r\n"
		+"                return this."+ this.currentBO.getClassNameLow() + "BS.findByPaginator(paginator, detachedCriteria);\r\n"
		+"        }\r\n"
		+"}";

		
	}

	private void buildBBStr() {
		this.bbStr = 
		"package com.jifan.pssmis.web.backbean."+this.currentBO.getMdName()+";\r\n"
		+"import java.util.ArrayList;\r\n"
		+"import java.util.List;\r\n"
		+"import javax.annotation.PostConstruct;\r\n"
		+"import javax.faces.bean.ManagedBean;\r\n"
		+"import javax.faces.bean.ManagedProperty;\r\n"
		+"import com.jifan.pssmis.bs."+this.currentBO.getMdName()+".I"+ this.currentBO.getClassName() + "BS;\r\n"
		+"import com.jifan.pssmis.foundation.exception.PubShowMessage;\r\n"
		+"import com.jifan.pssmis.model.bo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "BO;\r\n"
		+"import com.jifan.pssmis.model.vo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "QueryVO;\r\n"
		+"import com.jifan.pssmis.web.backbean.base.BaseBean;\r\n"
		+"@ManagedBean(name = \""+ this.currentBO.getClassNameLow() + "BB\")\r\n"
		+"public class "+ this.currentBO.getClassName() + "BB extends BaseBean {\r\n"
		+"	private static final long serialVersionUID = -1054820662761921269L;\r\n"
		+"	private "+ this.currentBO.getClassName() + "QueryVO param = new "+ this.currentBO.getClassName() + "QueryVO();\r\n"
		+"	private "+ this.currentBO.getClassName() + "BO currentBO = new "+ this.currentBO.getClassName() + "BO();\r\n"
		+"	private List<"+ this.currentBO.getClassName() + "BO> resultList = new ArrayList<"+ this.currentBO.getClassName() + "BO>();\r\n"
		+"	@ManagedProperty(name = \""+ this.currentBO.getClassNameLow() + "BS\", value = \"#{"+ this.currentBO.getClassNameLow() + "BS}\")\r\n"
		+"	private I"+ this.currentBO.getClassName() + "BS "+ this.currentBO.getClassNameLow() + "BS;\r\n"
		+"	public "+ this.currentBO.getClassName() + "BB() {\r\n\r\n"
		+"	}\r\n\r\n"
		+"	@PostConstruct\r\n"
		+"	public void init(){\r\n\r\n"
		+"	}\r\n\r\n"
		+"	public void save() {\r\n"
		+"		try {\r\n"
		+"			this.setUserAndDate(currentBO);\r\n"
		+"			this."+ this.currentBO.getClassNameLow() + "BS.saveNotExist(this.currentBO);\r\n"
		+"			this.findByPager();\r\n"
		+"			PubShowMessage.showInfo(PubShowMessage.ADD);\r\n"
		+"		} catch (Exception e) {\r\n"
		+"			this.msg.setMainMsg(e);\r\n"
		+"		}\r\n"
		+"	}\r\n\r\n"
		+"	public void delete() {\r\n"
		+"		try {\r\n"
		+"			this."+ this.currentBO.getClassNameLow() + "BS.delete(this.currentBO);\r\n"
		+"			this.findByPager();\r\n"
		+"			PubShowMessage.showInfo(PubShowMessage.DELETE);\r\n"
		+"		} catch (Exception e) {\r\n"
		+"			this.msg.setMainMsg(e);\r\n"
		+"		}\r\n"
		+"	}\r\n\r\n"
		+"	public void add() {\r\n"
		+"		this.currentBO = new "+ this.currentBO.getClassName() + "BO();\r\n"
		+"	}\r\n\r\n"
		+"	public void findByPager() {\r\n"
		+"		try {\r\n"
		+"			this.resultList = this."+ this.currentBO.getClassNameLow() + "BS.find"+ this.currentBO.getClassName() + "ByParam(param);\r\n"
		+"		} catch (Exception e) {\r\n"
		+"			this.msg.setMainMsg(e);\r\n"
		+"		}\r\n"
		+"	}\r\n\r\n"
		+"	public "+ this.currentBO.getClassName() + "QueryVO getParam() {\r\n"
		+"		return param;\r\n"
		+"	}\r\n\r\n"
		+"	public void setParam("+ this.currentBO.getClassName() + "QueryVO param) {\r\n"
		+"		this.param = param;\r\n"
		+"	}\r\n\r\n"
		+"	public "+ this.currentBO.getClassName() + "BO getCurrentBO() {\r\n"
		+"		return currentBO;\r\n"
		+"	}\r\n\r\n"
		+"	public void setCurrentBO("+ this.currentBO.getClassName() + "BO currentBO) {\r\n"
		+"		this.currentBO = currentBO;\r\n"
		+"	}\r\n\r\n"
		+"	public List<"+ this.currentBO.getClassName() + "BO> getResultList() {\r\n"
		+"		return resultList;\r\n"
		+"	}\r\n\r\n"
		+"	public void setResultList(List<"+ this.currentBO.getClassName() + "BO> resultList) {\r\n"
		+"		this.resultList = resultList;\r\n"
		+"	}\r\n\r\n"
		+"	public I"+ this.currentBO.getClassName() + "BS get"+ this.currentBO.getClassName() + "BS() {\r\n"
		+"		return "+ this.currentBO.getClassNameLow() + "BS;\r\n"
		+"	}\r\n\r\n"
		+"	public void set"+ this.currentBO.getClassName() + "BS(I"+ this.currentBO.getClassName() + "BS "+ this.currentBO.getClassNameLow() + "BS) {\r\n"
		+"		this."+ this.currentBO.getClassNameLow() + "BS = "+ this.currentBO.getClassNameLow() + "BS;\r\n"
		+"	}\r\n\r\n"
		+"}";
	}
	
	private void buildBBUccStr() {
		this.bbUccStr = 
		"package com.jifan.pssmis.web.backbean."+this.currentBO.getMdName()+";\r\n"
		+"import java.util.ArrayList;\r\n"
		+"import java.util.List;\r\n"
		+"import javax.annotation.PostConstruct;\r\n"
		+"import javax.faces.bean.ManagedBean;\r\n"
		+"import javax.faces.bean.ManagedProperty;\r\n"
		+"import com.jifan.pssmis.UCC."+this.currentBO.getMdName()+".I"+ this.currentBO.getClassName() + "UCC;\r\n"
		+"import com.jifan.pssmis.foundation.exception.PuUCChowMessage;\r\n"
		+"import com.jifan.pssmis.model.bo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "BO;\r\n"
		+"import com.jifan.pssmis.model.vo."+this.currentBO.getMdName()+"."+ this.currentBO.getClassName() + "QueryVO;\r\n"
		+"import com.jifan.pssmis.web.backbean.base.BaseBean;\r\n"
		+"@ManagedBean(name = \""+ this.currentBO.getClassNameLow() + "BB\")\r\n"
		+"public class "+ this.currentBO.getClassName() + "BB extends BaseBean {\r\n"
		+"	private static final long serialVersionUID = -1054820662761921269L;\r\n"
		+"	private "+ this.currentBO.getClassName() + "QueryVO param = new "+ this.currentBO.getClassName() + "QueryVO();\r\n"
		+"	private "+ this.currentBO.getClassName() + "BO currentBO = new "+ this.currentBO.getClassName() + "BO();\r\n"
		+"	private List<"+ this.currentBO.getClassName() + "BO> resultList = new ArrayList<"+ this.currentBO.getClassName() + "BO>();\r\n"
		+"	@ManagedProperty(name = \""+ this.currentBO.getClassNameLow() + "UCC\", value = \"#{"+ this.currentBO.getClassNameLow() + "UCC}\")\r\n"
		+"	private I"+ this.currentBO.getClassName() + "UCC "+ this.currentBO.getClassNameLow() + "UCC;\r\n"
		+"	public "+ this.currentBO.getClassName() + "BB() {\r\n\r\n"
		+"	}\r\n\r\n"
		+"	@PostConstruct\r\n"
		+"	public void init(){\r\n\r\n"
		+"	}\r\n\r\n"
		+"	public void save() {\r\n"
		+"		try {\r\n"
		+"			this.setUserAndDate(currentBO);\r\n"
		+"			this."+ this.currentBO.getClassNameLow() + "UCC.saveNotExist(this.currentBO);\r\n"
		+"			this.findByPager();\r\n"
		+"			PuUCChowMessage.showInfo(PuUCChowMessage.ADD);\r\n"
		+"		} catch (Exception e) {\r\n"
		+"			this.msg.setMainMsg(e);\r\n"
		+"		}\r\n"
		+"	}\r\n\r\n"
		+"	public void delete() {\r\n"
		+"		try {\r\n"
		+"			this."+ this.currentBO.getClassNameLow() + "UCC.delete(this.currentBO);\r\n"
		+"			this.findByPager();\r\n"
		+"			PuUCChowMessage.showInfo(PuUCChowMessage.DELETE);\r\n"
		+"		} catch (Exception e) {\r\n"
		+"			this.msg.setMainMsg(e);\r\n"
		+"		}\r\n"
		+"	}\r\n\r\n"
		+"	public void add() {\r\n"
		+"		this.currentBO = new "+ this.currentBO.getClassName() + "BO();\r\n"
		+"	}\r\n\r\n"
		+"	public void findByPager() {\r\n"
		+"		try {\r\n"
		+"			this.resultList = this."+ this.currentBO.getClassNameLow() + "UCC.find"+ this.currentBO.getClassName() + "ByParam(param);\r\n"
		+"		} catch (Exception e) {\r\n"
		+"			this.msg.setMainMsg(e);\r\n"
		+"		}\r\n"
		+"	}\r\n\r\n"
		+"	public "+ this.currentBO.getClassName() + "QueryVO getParam() {\r\n"
		+"		return param;\r\n"
		+"	}\r\n\r\n"
		+"	public void setParam("+ this.currentBO.getClassName() + "QueryVO param) {\r\n"
		+"		this.param = param;\r\n"
		+"	}\r\n\r\n"
		+"	public "+ this.currentBO.getClassName() + "BO getCurrentBO() {\r\n"
		+"		return currentBO;\r\n"
		+"	}\r\n\r\n"
		+"	public void setCurrentBO("+ this.currentBO.getClassName() + "BO currentBO) {\r\n"
		+"		this.currentBO = currentBO;\r\n"
		+"	}\r\n\r\n"
		+"	public List<"+ this.currentBO.getClassName() + "BO> getResultList() {\r\n"
		+"		return resultList;\r\n"
		+"	}\r\n\r\n"
		+"	public void setResultList(List<"+ this.currentBO.getClassName() + "BO> resultList) {\r\n"
		+"		this.resultList = resultList;\r\n"
		+"	}\r\n\r\n"
		+"	public I"+ this.currentBO.getClassNameLow() + "UCC get"+ this.currentBO.getClassName() + "UCC() {\r\n"
		+"		return "+ this.currentBO.getClassNameLow() + "UCC;\r\n"
		+"	}\r\n\r\n"
		+"	public void set"+ this.currentBO.getClassName() + "UCC(I"+ this.currentBO.getClassName() + "UCC "+ this.currentBO.getClassNameLow() + "UCC) {\r\n"
		+"		this."+ this.currentBO.getClassNameLow() + "UCC = "+ this.currentBO.getClassNameLow() + "UCC;\r\n"
		+"	}\r\n\r\n"
		+"}";
	}


	private void buildPFSStr() {
this.pfStr = 
"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">    \r\n"          
+"<html xmlns=\"http://www.w3.org/1999/xhtml\"                                                                                    \r\n"
+"	xmlns:h=\"http://java.sun.com/jsf/html\"                                                                                      \r\n"
+"	xmlns:f=\"http://java.sun.com/jsf/core\"                                                                                      \r\n"
+"	xmlns:ui=\"http://java.sun.com/jsf/facelets\"                                                                                 \r\n"
+"	xmlns:p=\"http://primefaces.org/ui\">                                                                                         \r\n"
+"	<h:form>                                                                                                                      \r\n"
+"                                                                                                                                \r\n"
+"		<p:outputPanel id=\""+this.currentBO.getClassNameLow()+"Panel\" autoUpdate=\"false\">                                                                   \r\n"
+"			<p:panelGrid columns=\"6\">                                                                                               \r\n"
+"                                                                                                                                \r\n"
+"				<h:outputLabel value=\"编码:\" />                                                                                       \r\n"
+"				<p:inputText id=\"code\" value=\"#{"+this.currentBO.getClassNameLow()+"BB.param.code}\" />                                                          \r\n"
+"				<h:outputLabel value=\"名称:\" />                                                                                       \r\n"
+"				<p:inputText id=\"name\" value=\"#{"+this.currentBO.getClassNameLow()+"BB.param.name}\" />                                                          \r\n"
+"                                                                                                                                \r\n"
+"				<f:facet name=\"footer\">                                                                                               \r\n"
+"					<p:commandButton value=\"查询\" action=\"#{"+this.currentBO.getClassNameLow()+"BB.findByPager}\"                                                  \r\n"
+"						update=\"dataTable"+this.currentBO.getClassNameLow()+"\" process=\""+this.currentBO.getClassNameLow()+"Panel\"                                                              \r\n"
+"						rendered=\"#{"+this.currentBO.getClassNameLow()+"BB.queryable}\" />                                                                             \r\n"
+"					<p:commandButton id=\"add"+this.currentBO.getClassNameLow()+"\" value=\"添加\"                                                                    \r\n"
+"						action=\"#{"+this.currentBO.getClassNameLow()+"BB.add}\" update=\"@form:"+this.currentBO.getClassNameLow()+"Dialog\"                                                        \r\n"
+"						oncomplete=\"PF('"+this.currentBO.getClassNameLow()+"Dialog').show();\" rendered=\"false\" />                                                   \r\n"
+"					<p:commandButton id=\"addeaesStyle\" value=\"添加\" process=\"@this\"                                                 \r\n"
+"						action=\"#{"+this.currentBO.getClassNameLow()+"BB.add}\" update=\"@widgetVar("+this.currentBO.getClassNameLow()+"Dialog)\"                                                  \r\n"
+"						rendered=\"#{"+this.currentBO.getClassNameLow()+"BB.addable}\"                                                                                  \r\n"
+"						oncomplete=\"PF('"+this.currentBO.getClassNameLow()+"Dialog').show();\" />                                                                      \r\n"
+"				</f:facet>                                                                                                              \r\n"
+"			</p:panelGrid>                                                                                                            \r\n"
+"			<p:dataTable id=\"dataTable"+this.currentBO.getClassNameLow()+"\" var=\"row\" resizableColumns=\"true\"                                               \r\n"
+"				value=\"#{"+this.currentBO.getClassNameLow()+"BB.resultList}\" paginator=\"true\"                                                                   \r\n"
+"				rows=\"#{"+this.currentBO.getClassNameLow()+"BB.pager.pageSize}\" rowIndexVar=\"idx\"                                                               \r\n"
+"				paginatorTemplate=\"{CurrentPageReport}  {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} \"\r\n"
+"				paginatorPosition=\"bottom\">                                                                                           \r\n"
+"				<p:column headerText=\"#\" style=\"width:30px\">                                                                        \r\n"
+"					<h:outputText value=\"#{idx+1}\" />                                                                                   \r\n"
+"				</p:column>                                                                                                             \r\n"
+"				<p:column headerText=\"操作\" styleClass=\"process-column\">                                                            \r\n"
+"					<p:commandLink id=\""+this.currentBO.getClassNameLow()+"EditBt\" value=\"编辑 \" process=\"@this\"                                                \r\n"
+"						rendered=\"#{"+this.currentBO.getClassNameLow()+"BB.editable}\"                                                                                 \r\n"
+"						update=\"@widgetVar("+this.currentBO.getClassNameLow()+"Dialog)\"                                                                               \r\n"
+"						oncomplete=\"PF('"+this.currentBO.getClassNameLow()+"Dialog').show();\">                                                                        \r\n"
+"						<f:setPropertyActionListener target=\"#{"+this.currentBO.getClassNameLow()+"BB.currentBO}\"                                                     \r\n"
+"							value=\"#{row}\" />                                                                                               \r\n"
+"					</p:commandLink>                                                                                                      \r\n"
+"					<h:outputText value=\" | \" />                                                                                        \r\n"
+"                                                                                                                                \r\n"
+"					<p:commandLink value=\"删除 \"                                                                                        \r\n"
+"						 process=\"@this\" action=\"#{"+this.currentBO.getClassNameLow()+"BB.delete}\"                                                                  \r\n"
+"						rendered=\"#{"+this.currentBO.getClassNameLow()+"BB.deleteable}\"                                                                               \r\n"
+"						update=\"dataTable"+this.currentBO.getClassNameLow()+"\">                                                                                       \r\n"
+"						<f:setPropertyActionListener target=\"#{"+this.currentBO.getClassNameLow()+"BB.currentBO}\"                                                     \r\n"
+"							value=\"#{row}\" />                                                                                               \r\n"
+"						<p:confirm header=\"删除提醒\" message=\"你 确定要删除?\"  icon=\"ui-icon-alert\" />                                \r\n"
+"					</p:commandLink>                                                                                                      \r\n"
+"                                                                                                                                \r\n"
+"				</p:column>                                                                                                             \r\n"
+"                                                                                                                                \r\n";

for (CodeDtlBO dtl : this.dtlList) {
	this.pfStr = this.pfStr +"				<p:column headerText=\""+dtl.getMemo()+"\">                                                                                      \r\n"
			+"					<h:outputText value=\"#{row."+dtl.getFieldNameLow()+"}\" />                                                                                \r\n"
			+"				</p:column>                                                                                                             \r\n\r\n";	
}
this.pfStr = this.pfStr 
+"			</p:dataTable>                                                                                                            \r\n\r\n"
+"		</p:outputPanel>                                                                                                            \r\n"
+"                                                                                                                                \r\n"
+"		<p:dialog header=\""+this.currentBO.getMemo()+"编辑\" draggable=\"true\" widgetVar=\""+this.currentBO.getClassNameLow()+"Dialog\"                                               \r\n"
+"			minimizable=\"false\" maximizable=\"false\" resizable=\"false\"                                                           \r\n"
+"			modal=\"false\" id=\""+this.currentBO.getClassNameLow()+"Dialog\">                                                                                    \r\n"
+"			<h:panelGrid columns=\"2\" id=\""+this.currentBO.getClassNameLow()+"DialogPanel\">                                                                    \r\n";

for (CodeDtlBO dtl : this.dtlList) {
	this.pfStr = this.pfStr     +"				<h:outputLabel value=\""+dtl.getMemo()+"\" />                                                                                   \r\n";
	if(dtl.getFieldType().equals("String") && Integer.valueOf(dtl.getLength())<=255)
		this.pfStr = this.pfStr     +"				<p:inputText value=\"#{"+this.currentBO.getClassNameLow()+"BB.currentBO."+dtl.getFieldNameLow()+"}\" maxlength=\""+dtl.getLength()+"\" />     \r\n"; 
	else
		this.pfStr = this.pfStr     +"				<p:inputTextarea value=\"#{"+this.currentBO.getClassNameLow()+"BB.currentBO.address}\" maxlength=\"100\" rows=\"3\" cols=\"33\" />  \r\n";
}
this.pfStr = this.pfStr
+"				<h:outputLabel value=\"地址:\" />                                                                                       \r\n"
+"				<p:inputTextarea value=\"#{"+this.currentBO.getClassNameLow()+"BB.currentBO.address}\" maxlength=\"100\" rows=\"3\" cols=\"33\" />  \r\n"
+"                                                                                                                                \r\n"
+"                                                                                                                                \r\n"
+"				<f:facet name=\"footer\">                                                                                               \r\n"
+"					<p:commandButton value=\"保存\" action=\"#{"+this.currentBO.getClassNameLow()+"BB.save}\" ajax=\"true\"                                           \r\n"
+"						process=\""+this.currentBO.getClassNameLow()+"DialogPanel\"                                                                                     \r\n"
+"						update=\""+this.currentBO.getClassNameLow()+"DialogPanel,@widgetVar(growl),dataTable"+this.currentBO.getClassNameLow()+"\"                                                  \r\n"
+"						oncomplete=\"PF('"+this.currentBO.getClassNameLow()+"Dialog').hide();\" />                                                                      \r\n"
+"					<p:commandButton value=\"取消\" onclick=\"PF('"+this.currentBO.getClassNameLow()+"Dialog').hide();\" />                                           \r\n"
+"				</f:facet>                                                                                                              \r\n"
+"                                                                                                                                \r\n"
+"			</h:panelGrid>                                                                                                            \r\n"
+"		</p:dialog>                                                                                                                 \r\n"
+"                                                                                                                                \r\n"
+"                                                                                                                                \r\n"
+"	</h:form>                                                                                                                     \r\n"
+"</html>                                                                                                                         \r\n"

;

	}

	private void buildRFStr() {
		this.rfStr = "";
	}

	public void save() {
		try {
			this.setUserAndDate(currentBO);
			if (currentBO != null)
				this.codeBS.saveNotExist(currentBO);
			this.findByPager();
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void delete() {
		try {
			this.codeBS.delete(currentBO);
			this.resultList.remove(this.currentBO);
			PubShowMessage.showInfo(PubShowMessage.DELETE);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void onCodeSelect(SelectEvent event) {
		try {
			this.currentBO = (CodeBO) event.getObject();
			if (this.currentBO != null) {
				if (this.currentBO != null
						&& CommonUtil.isEmpty(this.currentBO.getId()))
					this.codeBS.saveNotExist(this.currentBO);
				CodeDtlQueryVO dtlqueryVO = new CodeDtlQueryVO();
				dtlqueryVO.setCodeID(this.currentBO.getId());
				this.dtlList = this.codeDtlBS.findCodeDtlByParam(dtlqueryVO);
			} else
				PubShowMessage.showInfo(PubShowMessage.DELETE);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void onCodeDtlSelect(SelectEvent event) {
		try {
			this.dtlBO = (CodeDtlBO) event.getObject();
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void add() {
		int num = 0;
		if (this.resultList != null)
			num = this.resultList.size() + 1;
		this.currentBO = new CodeBO("", this.getDefaultBagName(), num, "");
		this.resultList.add(currentBO);
	}

	public void addDtl() {
		if (this.dtlList == null)
			this.dtlList = new ArrayList<CodeDtlBO>();
		this.dtlList.add(new CodeDtlBO(this.currentBO.getId(), "", "",
				"String", dtlList.size() + 1, String.valueOf(255), String
						.valueOf(1), ""));
	}

	public void deleteDtl() {
		try {
			this.dtlList.remove(this.dtlBO);
			this.codeDtlBS.delete(dtlBO);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void saveDtlList() {
		try {
			for (CodeDtlBO dtl : this.dtlList) {
				this.codeDtlBS.saveNotExist(dtl);
			}
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public CodeQueryVO getParam() {
		return param;
	}

	public void setParam(CodeQueryVO param) {
		this.param = param;
	}

	public CodeBO getCurrentBO() {
		return currentBO;
	}

	public void setCurrentBO(CodeBO currentBO) {
		this.currentBO = currentBO;
	}

	public List<CodeBO> getResultList() {
		return resultList;
	}

	public void setResultList(List<CodeBO> resultList) {
		this.resultList = resultList;
	}

	public ICodeBS getCodeBS() {
		return codeBS;
	}

	public void setCodeBS(ICodeBS codeBS) {
		this.codeBS = codeBS;
	}

	public List<CodeDtlBO> getDtlList() {
		return dtlList;
	}

	public void setDtlList(List<CodeDtlBO> dtlList) {
		this.dtlList = dtlList;
	}

	public ICodeDtlBS getCodeDtlBS() {
		return codeDtlBS;
	}

	public void setCodeDtlBS(ICodeDtlBS codeDtlBS) {
		this.codeDtlBS = codeDtlBS;
	}

	public CodeDtlBO getDtlBO() {
		return dtlBO;
	}

	public void setDtlBO(CodeDtlBO dtlBO) {
		this.dtlBO = dtlBO;
	}

	public String getBoStr() {
		return boStr;
	}

	public void setBoStr(String boStr) {
		this.boStr = boStr;
	}

	public String getDefaultBagName() {
		return defaultBagName;
	}

	public void setDefaultBagName(String defaultBagName) {
		this.defaultBagName = defaultBagName;
	}

	public String getVoStr() {
		return voStr;
	}

	public void setVoStr(String voStr) {
		this.voStr = voStr;
	}

	public String getIdaoStr() {
		return idaoStr;
	}

	public void setIdaoStr(String idaoStr) {
		this.idaoStr = idaoStr;
	}

	public String getDaoStr() {
		return daoStr;
	}

	public void setDaoStr(String daoStr) {
		this.daoStr = daoStr;
	}

	public String getIbsStr() {
		return ibsStr;
	}

	public void setIbsStr(String ibsStr) {
		this.ibsStr = ibsStr;
	}

	public String getBsStr() {
		return bsStr;
	}

	public void setBsStr(String bsStr) {
		this.bsStr = bsStr;
	}

	public String getIuccStr() {
		return iuccStr;
	}

	public void setIuccStr(String iuccStr) {
		this.iuccStr = iuccStr;
	}

	public String getUccStr() {
		return uccStr;
	}

	public void setUccStr(String uccStr) {
		this.uccStr = uccStr;
	}

	public String getPfStr() {
		return pfStr;
	}

	public void setPfStr(String pfStr) {
		this.pfStr = pfStr;
	}

	public String getRfStr() {
		return rfStr;
	}

	public void setRfStr(String rfStr) {
		this.rfStr = rfStr;
	}

	public String getBbStr() {
		return bbStr;
	}

	public void setBbStr(String bbStr) {
		this.bbStr = bbStr;
	}

	public String getBbUccStr() {
		return bbUccStr;
	}

	public void setBbUccStr(String bbUccStr) {
		this.bbUccStr = bbUccStr;
	}

}
