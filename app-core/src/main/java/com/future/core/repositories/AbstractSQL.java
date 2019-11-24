/**
 * Copyright 2009-2015 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.future.core.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copy and modified to support INSERT SELECT
 * @author Clinton Begin
 * @author Jeff Butler
 * @author Adam Gent
 * @author Ruijin Ma
 */
public abstract class AbstractSQL<T> {

    private static final String AND = ") \nAND (";
    private static final String OR = ") \nOR (";

    private SQLStatement sql = new SQLStatement();

    public abstract T getSelf();

    public T UPDATE(String table) {
        sql().statementType = SQLStatement.StatementType.UPDATE;
        sql().tables.add(table);
        return getSelf();
    }

    public T SET(String sets) {
        sql().sets.add(sets);
        return getSelf();
    }

    public T SET_IF(String sets, boolean condition) {
        return condition ? SET(sets) : getSelf();
    }

    public T SET_IF(String sets, boolean condition, String otherwise) {
        return condition ? SET(sets) : SET(otherwise);
    }

    public T INSERT_INTO(String tableName) {
        sql().statementType = SQLStatement.StatementType.INSERT;
        sql().tables.add(tableName);
        return getSelf();
    }

    public T VALUES(String columns, String values) {
        sql().columns.add(columns);
        sql().values.add(values);
        return getSelf();
    }

    public T VALUES_IF(String columns, String values, boolean condition) {
        return condition ? VALUES(columns, values) : getSelf();
    }

    public T VALUES_IF(String columns, String values, String otherwise, boolean condition) {
        return condition ? VALUES(columns, values) : VALUES(columns, otherwise);
    }

    public T SELECT(String columns) {
        sql().statementType = SQLStatement.StatementType.SELECT;
        sql().select.add(columns);
        return getSelf();
    }

    public T SELECT(String columns, String values) {
        sql().insertSelect = true;
        return VALUES(columns, values);
    }

    public T SELECT_DISTINCT(String columns) {
        sql().distinct = true;
        return SELECT(columns);
    }

    public T SELECT_DISTINCT(String columns, String values) {
        sql().distinct = true;
        return SELECT(columns, values);
    }

    public T FOR_UPDATE() {
        sql().forUpdate = true;
        return getSelf();
    }

    public T DELETE_FROM(String table) {
        sql().statementType = SQLStatement.StatementType.DELETE;
        sql().tables.add(table);
        return getSelf();
    }

    public T FROM(String table) {
        sql().tables.add(table);
        return getSelf();
    }

    public T JOIN(String join) {
        sql().join.add(join);
        return getSelf();
    }

    public T INNER_JOIN(String join) {
        sql().innerJoin.add(join);
        return getSelf();
    }

    public T LEFT_JOIN(String join) {
        return LEFT_OUTER_JOIN(join);
    }

    public T RIGHT_JOIN(String join) {
        return RIGHT_OUTER_JOIN(join);
    }

    public T LEFT_OUTER_JOIN(String join) {
        sql().leftOuterJoin.add(join);
        return getSelf();
    }

    public T RIGHT_OUTER_JOIN(String join) {
        sql().rightOuterJoin.add(join);
        return getSelf();
    }

    public T OUTER_JOIN(String join) {
        sql().outerJoin.add(join);
        return getSelf();
    }

    public T WHERE_IF(String conditions, boolean condition) {
        return condition ? WHERE(conditions) : getSelf();
    }

    public T WHERE(String conditions) {
        sql().where.add(conditions);
        sql().lastList = sql().where;
        return getSelf();
    }

    public T OR() {
        sql().lastList.add(OR);
        return getSelf();
    }

    public T AND() {
        sql().lastList.add(AND);
        return getSelf();
    }

    public T GROUP_BY(String columns) {
        sql().groupBy.add(columns);
        return getSelf();
    }

    public T HAVING(String conditions) {
        sql().having.add(conditions);
        sql().lastList = sql().having;
        return getSelf();
    }

    public T ORDER_BY(String columns) {
        sql().orderBy.add(columns);
        return getSelf();
    }

    protected SQLStatement sql() {
        return sql;
    }

    public <A extends Appendable> A usingAppender(A a) {
        sql().sql(a);
        return a;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sql().sql(sb);
        return sb.toString();
    }

    private static class SafeAppendable {
        private final Appendable a;
        private boolean empty = true;

        public SafeAppendable(Appendable a) {
            super();
            this.a = a;
        }

        public SafeAppendable append(CharSequence s) {
            try {
                if (empty && s.length() > 0) {
                    empty = false;
                }
                a.append(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        public boolean isEmpty() {
            return empty;
        }

    }

    private static class SQLStatement {

        public enum StatementType {
            DELETE, INSERT, SELECT, UPDATE
        }

        StatementType statementType;
        List<String> sets = new ArrayList<>();
        List<String> select = new ArrayList<>();
        List<String> tables = new ArrayList<>();
        List<String> join = new ArrayList<>();
        List<String> innerJoin = new ArrayList<>();
        List<String> outerJoin = new ArrayList<>();
        List<String> leftOuterJoin = new ArrayList<>();
        List<String> rightOuterJoin = new ArrayList<>();
        List<String> where = new ArrayList<>();
        List<String> having = new ArrayList<>();
        List<String> groupBy = new ArrayList<>();
        List<String> orderBy = new ArrayList<>();
        List<String> lastList = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();
        boolean distinct;
        boolean insertSelect;
        boolean forUpdate;

        public SQLStatement() {
            // Prevent Synthetic Access
        }

        private void sqlClause(SafeAppendable builder, String keyword, List<String> parts, String open, String close,
                               String conjunction) {
            if (!parts.isEmpty()) {
                if (!builder.isEmpty()) {
                    builder.append("\n");
                }
                builder.append(keyword);
                builder.append(" ");
                builder.append(open);
                String last = "________";
                for (int i = 0, n = parts.size(); i < n; i++) {
                    String part = parts.get(i);
                    if (i > 0 && !part.equals(AND) && !part.equals(OR) && !last.equals(AND) && !last.equals(OR)) {
                        builder.append(conjunction);
                    }
                    builder.append(part);
                    last = part;
                }
                builder.append(close);
            }
        }

        private String selectSQL(SafeAppendable builder) {
            if (distinct) {
                sqlClause(builder, "SELECT DISTINCT", statementType == StatementType.SELECT ? select : values, "", "", ", ");
            } else {
                sqlClause(builder, "SELECT", statementType == StatementType.SELECT ? select : values, "", "", ", ");
            }

            if (statementType == StatementType.SELECT) {
                sqlClause(builder, "FROM", tables, "", "", ", ");
            } else {
                sqlClause(builder, "FROM", tables.subList(1, tables.size()), "", "", ", ");
            }

            sqlClause(builder, "JOIN", join, "", "", "\nJOIN ");
            sqlClause(builder, "INNER JOIN", innerJoin, "", "", "\nINNER JOIN ");
            sqlClause(builder, "OUTER JOIN", outerJoin, "", "", "\nOUTER JOIN ");
            sqlClause(builder, "LEFT OUTER JOIN", leftOuterJoin, "", "", "\nLEFT OUTER JOIN ");
            sqlClause(builder, "RIGHT OUTER JOIN", rightOuterJoin, "", "", "\nRIGHT OUTER JOIN ");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            sqlClause(builder, "GROUP BY", groupBy, "", "", ", ");
            sqlClause(builder, "HAVING", having, "(", ")", " AND ");
            sqlClause(builder, "ORDER BY", orderBy, "", "", ", ");
            return builder.toString() + (statementType == StatementType.SELECT && forUpdate ? " FOR UPDATE" : "");
        }

        private String insertSQL(SafeAppendable builder) {
            sqlClause(builder, "INSERT INTO", tables.subList(0, 1), "", "", "");
            sqlClause(builder, "", columns, "(", ")", ", ");

            if (insertSelect) {
                selectSQL(builder);
            } else {
                sqlClause(builder, "VALUES", values, "(", ")", ", ");
            }
            return builder.toString();
        }

        private String deleteSQL(SafeAppendable builder) {
            sqlClause(builder, "DELETE FROM", tables.subList(0, 1), "", "", "");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            return builder.toString();
        }

        private String updateSQL(SafeAppendable builder) {
            sqlClause(builder, "UPDATE", tables.subList(0, 1), "", "", "");
            sqlClause(builder, "SET", sets, "", "", ", ");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            return builder.toString();
        }

        public String sql(Appendable a) {
            SafeAppendable builder = new SafeAppendable(a);
            if (statementType == null) {
                return null;
            }

            String answer;

            switch (statementType) {
                case DELETE:
                    answer = deleteSQL(builder);
                    break;

                case INSERT:
                    answer = insertSQL(builder);
                    break;

                case SELECT:
                    answer = selectSQL(builder);
                    break;

                case UPDATE:
                    answer = updateSQL(builder);
                    break;

                default:
                    answer = null;
            }

            return answer;
        }
    }
}
