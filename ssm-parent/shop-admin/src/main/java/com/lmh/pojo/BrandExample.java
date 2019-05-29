package com.lmh.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BrandExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("brand_id is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Long value) {
            addCriterion("brand_id =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Long value) {
            addCriterion("brand_id <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Long value) {
            addCriterion("brand_id >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Long value) {
            addCriterion("brand_id >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Long value) {
            addCriterion("brand_id <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Long value) {
            addCriterion("brand_id <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Long> values) {
            addCriterion("brand_id in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Long> values) {
            addCriterion("brand_id not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Long value1, Long value2) {
            addCriterion("brand_id between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Long value1, Long value2) {
            addCriterion("brand_id not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNull() {
            addCriterion("brand_name is null");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNotNull() {
            addCriterion("brand_name is not null");
            return (Criteria) this;
        }

        public Criteria andBrandNameEqualTo(String value) {
            addCriterion("brand_name =", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotEqualTo(String value) {
            addCriterion("brand_name <>", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThan(String value) {
            addCriterion("brand_name >", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThanOrEqualTo(String value) {
            addCriterion("brand_name >=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThan(String value) {
            addCriterion("brand_name <", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThanOrEqualTo(String value) {
            addCriterion("brand_name <=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLike(String value) {
            addCriterion("brand_name like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotLike(String value) {
            addCriterion("brand_name not like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameIn(List<String> values) {
            addCriterion("brand_name in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotIn(List<String> values) {
            addCriterion("brand_name not in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameBetween(String value1, String value2) {
            addCriterion("brand_name between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotBetween(String value1, String value2) {
            addCriterion("brand_name not between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andBrandWebIsNull() {
            addCriterion("brand_web is null");
            return (Criteria) this;
        }

        public Criteria andBrandWebIsNotNull() {
            addCriterion("brand_web is not null");
            return (Criteria) this;
        }

        public Criteria andBrandWebEqualTo(String value) {
            addCriterion("brand_web =", value, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebNotEqualTo(String value) {
            addCriterion("brand_web <>", value, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebGreaterThan(String value) {
            addCriterion("brand_web >", value, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebGreaterThanOrEqualTo(String value) {
            addCriterion("brand_web >=", value, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebLessThan(String value) {
            addCriterion("brand_web <", value, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebLessThanOrEqualTo(String value) {
            addCriterion("brand_web <=", value, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebLike(String value) {
            addCriterion("brand_web like", value, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebNotLike(String value) {
            addCriterion("brand_web not like", value, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebIn(List<String> values) {
            addCriterion("brand_web in", values, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebNotIn(List<String> values) {
            addCriterion("brand_web not in", values, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebBetween(String value1, String value2) {
            addCriterion("brand_web between", value1, value2, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandWebNotBetween(String value1, String value2) {
            addCriterion("brand_web not between", value1, value2, "brandWeb");
            return (Criteria) this;
        }

        public Criteria andBrandLogoIsNull() {
            addCriterion("brand_logo is null");
            return (Criteria) this;
        }

        public Criteria andBrandLogoIsNotNull() {
            addCriterion("brand_logo is not null");
            return (Criteria) this;
        }

        public Criteria andBrandLogoEqualTo(String value) {
            addCriterion("brand_logo =", value, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoNotEqualTo(String value) {
            addCriterion("brand_logo <>", value, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoGreaterThan(String value) {
            addCriterion("brand_logo >", value, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoGreaterThanOrEqualTo(String value) {
            addCriterion("brand_logo >=", value, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoLessThan(String value) {
            addCriterion("brand_logo <", value, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoLessThanOrEqualTo(String value) {
            addCriterion("brand_logo <=", value, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoLike(String value) {
            addCriterion("brand_logo like", value, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoNotLike(String value) {
            addCriterion("brand_logo not like", value, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoIn(List<String> values) {
            addCriterion("brand_logo in", values, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoNotIn(List<String> values) {
            addCriterion("brand_logo not in", values, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoBetween(String value1, String value2) {
            addCriterion("brand_logo between", value1, value2, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandLogoNotBetween(String value1, String value2) {
            addCriterion("brand_logo not between", value1, value2, "brandLogo");
            return (Criteria) this;
        }

        public Criteria andBrandDescIsNull() {
            addCriterion("brand_desc is null");
            return (Criteria) this;
        }

        public Criteria andBrandDescIsNotNull() {
            addCriterion("brand_desc is not null");
            return (Criteria) this;
        }

        public Criteria andBrandDescEqualTo(String value) {
            addCriterion("brand_desc =", value, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescNotEqualTo(String value) {
            addCriterion("brand_desc <>", value, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescGreaterThan(String value) {
            addCriterion("brand_desc >", value, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescGreaterThanOrEqualTo(String value) {
            addCriterion("brand_desc >=", value, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescLessThan(String value) {
            addCriterion("brand_desc <", value, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescLessThanOrEqualTo(String value) {
            addCriterion("brand_desc <=", value, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescLike(String value) {
            addCriterion("brand_desc like", value, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescNotLike(String value) {
            addCriterion("brand_desc not like", value, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescIn(List<String> values) {
            addCriterion("brand_desc in", values, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescNotIn(List<String> values) {
            addCriterion("brand_desc not in", values, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescBetween(String value1, String value2) {
            addCriterion("brand_desc between", value1, value2, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandDescNotBetween(String value1, String value2) {
            addCriterion("brand_desc not between", value1, value2, "brandDesc");
            return (Criteria) this;
        }

        public Criteria andBrandStatusIsNull() {
            addCriterion("brand_status is null");
            return (Criteria) this;
        }

        public Criteria andBrandStatusIsNotNull() {
            addCriterion("brand_status is not null");
            return (Criteria) this;
        }

        public Criteria andBrandStatusEqualTo(Integer value) {
            addCriterion("brand_status =", value, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandStatusNotEqualTo(Integer value) {
            addCriterion("brand_status <>", value, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandStatusGreaterThan(Integer value) {
            addCriterion("brand_status >", value, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_status >=", value, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandStatusLessThan(Integer value) {
            addCriterion("brand_status <", value, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandStatusLessThanOrEqualTo(Integer value) {
            addCriterion("brand_status <=", value, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandStatusIn(List<Integer> values) {
            addCriterion("brand_status in", values, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandStatusNotIn(List<Integer> values) {
            addCriterion("brand_status not in", values, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandStatusBetween(Integer value1, Integer value2) {
            addCriterion("brand_status between", value1, value2, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_status not between", value1, value2, "brandStatus");
            return (Criteria) this;
        }

        public Criteria andBrandOrderIsNull() {
            addCriterion("brand_order is null");
            return (Criteria) this;
        }

        public Criteria andBrandOrderIsNotNull() {
            addCriterion("brand_order is not null");
            return (Criteria) this;
        }

        public Criteria andBrandOrderEqualTo(Integer value) {
            addCriterion("brand_order =", value, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andBrandOrderNotEqualTo(Integer value) {
            addCriterion("brand_order <>", value, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andBrandOrderGreaterThan(Integer value) {
            addCriterion("brand_order >", value, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andBrandOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_order >=", value, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andBrandOrderLessThan(Integer value) {
            addCriterion("brand_order <", value, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andBrandOrderLessThanOrEqualTo(Integer value) {
            addCriterion("brand_order <=", value, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andBrandOrderIn(List<Integer> values) {
            addCriterion("brand_order in", values, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andBrandOrderNotIn(List<Integer> values) {
            addCriterion("brand_order not in", values, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andBrandOrderBetween(Integer value1, Integer value2) {
            addCriterion("brand_order between", value1, value2, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andBrandOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_order not between", value1, value2, "brandOrder");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNull() {
            addCriterion("modified_time is null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNotNull() {
            addCriterion("modified_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeEqualTo(Date value) {
            addCriterion("modified_time =", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotEqualTo(Date value) {
            addCriterion("modified_time <>", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThan(Date value) {
            addCriterion("modified_time >", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_time >=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThan(Date value) {
            addCriterion("modified_time <", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThanOrEqualTo(Date value) {
            addCriterion("modified_time <=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIn(List<Date> values) {
            addCriterion("modified_time in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotIn(List<Date> values) {
            addCriterion("modified_time not in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeBetween(Date value1, Date value2) {
            addCriterion("modified_time between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotBetween(Date value1, Date value2) {
            addCriterion("modified_time not between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}