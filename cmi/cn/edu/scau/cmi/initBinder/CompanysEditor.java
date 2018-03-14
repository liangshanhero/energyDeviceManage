package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CompanysEditor")
public class CompanysEditor extends PropertyEditorSupport {

	@Autowired
	private CompanyDAO companyDAO;

	public void setAsText(String companys) {

		Set<Company> companySet = new HashSet<Company>();
		if (companys.length() > 0) {
			String[] sourceCompanyStringSet = companys.split(",");
			if (sourceCompanyStringSet != null) {
				for (String companyStringSet : sourceCompanyStringSet) {
					String[] companyIdStringSet = companyStringSet
							.split("\\] ");
					String[] companyIdString1 = companyIdStringSet[0]
							.split("\\=\\[");
					Company company = companyDAO
							.findCompanyByPrimaryKey(Integer
									.parseInt(companyIdString1[1]));
					companySet.add(company);
				}
			}
			setValue(companySet);
		} else
			setValue(null);
	}
}