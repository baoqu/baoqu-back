(ns baoqu.test.db.users
  (:use [clojure.test])
  (:require [baoqu.db.users :as users]
            [baoqu.init.fixtures :as fix]
            [baoqu.utils.functions :as fn]))

(def sample-user {:username "john.doe@corporation.com"})

(deftest create
  (testing "creating a new user"
    (fix/create-drop)
    (let [saved (users/create sample-user)]
      (is (fn/not-nil? :id))
      (is (= (:username saved) "john.doe@corporation.com")))))

(deftest list-all
  (testing "when listing all users"
    (fix/create-drop)
    (users/create {:username "ah"})
    (users/create {:username "oh"})
    (is (= (count (users/find-all)) 2))))
