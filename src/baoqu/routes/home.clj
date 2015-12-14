(ns baoqu.routes.home
  (:require
   [catacumba.http :as http]
   [baoqu.utils.mime :as mime]
   [baoqu.services.meta :as meta]))

(defn hello-baoqu
  "Shows app information"
  [context]
  (http/ok
   (mime/to-json meta/app-info)
   {:content-type mime/application-json}))
