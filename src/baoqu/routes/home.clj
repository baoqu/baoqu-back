(ns baoqu.routes.home
  (:require [cheshire.core :as json]
            [catacumba.http :as http]
            [baoqu.utils.mime :as mime]
            [baoqu.services.meta :as meta]))

(defn hello-baoqu
  "shows App information"
  [context]
  (http/ok
   (json/generate-string meta/app-info)
   {:content-type mime/application-json}))
