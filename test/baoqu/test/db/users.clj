(ns baoqu.test.db.users
  (:use [clojure.test])
  (:require [baoqu.db.users :as users]
            [baoqu.utils.functions :as fn]))

(defn drop-create
  []
    (fn/try-execute users/drop-users-events-table)
    (fn/try-execute users/drop-table)
    (fn/try-execute users/create-table)
    (fn/try-execute users/create-users-events-table))

(def sample-user {:username "john.doe@corporation.com"})

(deftest create
  (testing "creating a new user"
    (drop-create)
    (let [saved (users/create sample-user)]
      (is (fn/not-nil? :id))
      (is (= (:username saved) "john.doe@corporation.com")))))
