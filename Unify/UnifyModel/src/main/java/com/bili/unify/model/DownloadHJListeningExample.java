package com.bili.unify.model;

import java.util.ArrayList;
import java.util.List;

public class DownloadHJListeningExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DownloadHJListeningExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFile_nameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFile_nameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFile_nameEqualTo(String value) {
            addCriterion("file_name =", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameGreaterThan(String value) {
            addCriterion("file_name >", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameLessThan(String value) {
            addCriterion("file_name <", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameLike(String value) {
            addCriterion("file_name like", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameNotLike(String value) {
            addCriterion("file_name not like", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameIn(List<String> values) {
            addCriterion("file_name in", values, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "file_name");
            return (Criteria) this;
        }

        public Criteria andDownload_urlIsNull() {
            addCriterion("download_url is null");
            return (Criteria) this;
        }

        public Criteria andDownload_urlIsNotNull() {
            addCriterion("download_url is not null");
            return (Criteria) this;
        }

        public Criteria andDownload_urlEqualTo(String value) {
            addCriterion("download_url =", value, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlNotEqualTo(String value) {
            addCriterion("download_url <>", value, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlGreaterThan(String value) {
            addCriterion("download_url >", value, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlGreaterThanOrEqualTo(String value) {
            addCriterion("download_url >=", value, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlLessThan(String value) {
            addCriterion("download_url <", value, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlLessThanOrEqualTo(String value) {
            addCriterion("download_url <=", value, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlLike(String value) {
            addCriterion("download_url like", value, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlNotLike(String value) {
            addCriterion("download_url not like", value, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlIn(List<String> values) {
            addCriterion("download_url in", values, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlNotIn(List<String> values) {
            addCriterion("download_url not in", values, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlBetween(String value1, String value2) {
            addCriterion("download_url between", value1, value2, "download_url");
            return (Criteria) this;
        }

        public Criteria andDownload_urlNotBetween(String value1, String value2) {
            addCriterion("download_url not between", value1, value2, "download_url");
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