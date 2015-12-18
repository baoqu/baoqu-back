(ns baoqu.db.common)

;; sqlite id identitier
(def column-id (keyword "last_insert_rowid()"))

;; Applied to a given query as second parameter returns only first row
(def just-first-row {:result-set-fn first})

(def connection {:classname   "org.sqlite.JDBC"
                 :subprotocol "sqlite"
                 :subname     "db.sq3"})

(defn q-find
  "Executes the query function passed as
  first parameter and returns only one row"
  [fn map]
  (fn map just-first-row))

(defn q-do-id
  "Executes the query function passed as
   first argument and returns the record id"
  [fn params]
  (let [id (get (fn params) column-id)]
    id))
