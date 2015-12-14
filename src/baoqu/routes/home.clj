(ns baoqu.routes.home
  (:require [cheshire.core :refer :all]
            [catacumba.http :as http]
            [baoqu.utils.mime :as mime]
            [baoqu.services.meta :as meta]))

(defn hello-baoqu
  "Shows App information"
  [context]
  (http/ok
   (generate-string meta/app-info)
   {:content-type mime/application-json}))
