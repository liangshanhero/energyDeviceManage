package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CompanyEditor")
public class CompanyEditor extends PropertyEditorSupport {

	@Autowired
	private CompanyDAO companyDAO;

	public void setAsText(String companyString) {
		Company company = new Company();
		if (companyString.length() > 0) {
			String[] companyStringSet = companyString.split("\\] ");
			String[] room2deviceId1 = companyStringSet[0].split("\\=\\[");

			company = companyDAO.findCompanyByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(company);
		} else
			setValue(null);
	}
}