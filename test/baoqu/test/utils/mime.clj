(ns baoqu.test.utils.mime
  (:require [baoqu.utils.mime :as utils]
            [clojure.test :refer :all]))

(deftest encode-decode-transit
  (testing "transit serialization"
    (let [source {:name "me" :age 100}]
      (is (= (-> source
                 (utils/to-ws)
                 (utils/from-ws)) source)))))
