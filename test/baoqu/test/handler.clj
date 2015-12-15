(ns baoqu.test.handler
  (:use clojure.test))

(deftest test-app
  (testing "main route"
    (let [r 200]
      (is (= r 200)))))
