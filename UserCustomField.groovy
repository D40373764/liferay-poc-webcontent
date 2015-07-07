import com.liferay.portal.service.ClassNameLocalServiceUtil
import com.liferay.portal.util.PortalUtil
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil
import com.liferay.portlet.expando.model.ExpandoTableConstants
import com.liferay.portlet.expando.model.ExpandoValue
import com.liferay.portal.service.UserLocalServiceUtil
import com.liferay.portal.NoSuchUserException

String customAttributeName="term-code"
String customAttributeValue="201540"

println "${customAttributeName}"

long classNameId = ClassNameLocalServiceUtil.getClassNameId(User.class)
long companyId = PortalUtil.getDefaultCompanyId()

List<ExpandoValue> values = ExpandoValueLocalServiceUtil.getColumnValues(companyId, classNameId, ExpandoTableConstants.DEFAULT_TABLE_NAME, customAttributeName,customAttributeValue, -1, -1);

println "Total user confirmed: ${values.size()}"
println ""
println "-- LIST --"

User user;

for (int i = 0; i < values.size(); i++) {
  long userId = values.get(i).getClassPK();
  try{
    user =  UserLocalServiceUtil.getUser(userId);
    println user.getFullName()
  }catch(NoSuchUserException e ){
    println e
  }
}
