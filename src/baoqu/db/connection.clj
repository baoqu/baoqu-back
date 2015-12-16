(ns baoqu.db.connection)

;; sqlite id identitier
(def column-id (keyword "last_insert_rowid()"))

;; Applied to a given query as second parameter returns only first row
(def just-first-row {:result-set-fn first})

(def connection {:classname   "org.sqlite.JDBC"
                 :subprotocol "sqlite"
                 :subname     "db.sq3"})
