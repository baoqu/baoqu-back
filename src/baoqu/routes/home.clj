(ns baoqu.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [cheshire.core :refer :all]
            [baoqu.utils.mime :as mime]
            [baoqu.services.meta :as meta]))

(defresource home
  :handle-ok (generate-string meta/app-info)
  :available-media-types [mime/application-json])

(defroutes home-routes
  (GET "/" request home))
