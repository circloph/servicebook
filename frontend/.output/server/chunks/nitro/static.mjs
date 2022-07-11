import { eventHandler, createError } from 'h3';
import { withLeadingSlash, withoutTrailingSlash, parseURL } from 'ufo';
import { promises } from 'fs';
import { resolve, dirname } from 'pathe';
import { fileURLToPath } from 'url';

const assets = {
  "/200.html": {
    "type": "text/html; charset=utf-8",
    "etag": "\"1f8-O8cqdld7TinxbYHe031RgrOzBoA\"",
    "mtime": "2022-06-27T10:05:42.792Z",
    "path": "../public/200.html"
  },
  "/404.html": {
    "type": "text/html; charset=utf-8",
    "etag": "\"1f8-O8cqdld7TinxbYHe031RgrOzBoA\"",
    "mtime": "2022-06-27T10:05:42.792Z",
    "path": "../public/404.html"
  },
  "/index.html": {
    "type": "text/html; charset=utf-8",
    "etag": "\"1f8-O8cqdld7TinxbYHe031RgrOzBoA\"",
    "mtime": "2022-06-27T10:05:42.792Z",
    "path": "../public/index.html"
  },
  "/_nuxt/_id_-2a8245eb.mjs": {
    "type": "application/javascript",
    "etag": "\"70b-hbTsbOcxY/1GvxCqMwtCJJapthk\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/_id_-2a8245eb.mjs"
  },
  "/_nuxt/_id_-91258440.mjs": {
    "type": "application/javascript",
    "etag": "\"1e16-irYqD2JCjgRygRRXwE/2FqgWE1s\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/_id_-91258440.mjs"
  },
  "/_nuxt/_id_-a274d628.mjs": {
    "type": "application/javascript",
    "etag": "\"eb0-EozsP7UQSuS+IKRSudko2+A97LY\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/_id_-a274d628.mjs"
  },
  "/_nuxt/auth-86f7c54a.mjs": {
    "type": "application/javascript",
    "etag": "\"89-y41+0hIMLQcqX/EwDG11fCgueIw\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/auth-86f7c54a.mjs"
  },
  "/_nuxt/authenticated-921aea1b.mjs": {
    "type": "application/javascript",
    "etag": "\"7f3-wxIWho6bFQK13Bei422hvpPqS/g\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/authenticated-921aea1b.mjs"
  },
  "/_nuxt/constructor-b5114084.mjs": {
    "type": "application/javascript",
    "etag": "\"1475-TKl4X7wwwCmfdasNIZ0Ow0eGedU\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/constructor-b5114084.mjs"
  },
  "/_nuxt/custom-8da721ca.mjs": {
    "type": "application/javascript",
    "etag": "\"1b6-nvdVme9wGUzVc/1XBxNsuzTDEgs\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/custom-8da721ca.mjs"
  },
  "/_nuxt/default-9f95556b.mjs": {
    "type": "application/javascript",
    "etag": "\"21a-BERCNE1oExsI7ZMpUzH8ET1Brzc\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/default-9f95556b.mjs"
  },
  "/_nuxt/entry-ced0cdb8.mjs": {
    "type": "application/javascript",
    "etag": "\"6d427-ZckgBR0LLYsCZP99Y9A1lb5Dznk\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/entry-ced0cdb8.mjs"
  },
  "/_nuxt/entry.8774b6e2.css": {
    "type": "text/css; charset=utf-8",
    "etag": "\"649da-zRGgM4F0URcDDYirzzhnef2G1R0\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/entry.8774b6e2.css"
  },
  "/_nuxt/index-3b0404f8.mjs": {
    "type": "application/javascript",
    "etag": "\"1526-B9X9iHCbrT4pQgLdNcKCfTBrjQw\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/index-3b0404f8.mjs"
  },
  "/_nuxt/index-76306c3d.mjs": {
    "type": "application/javascript",
    "etag": "\"e53-YM09RPYWiOLwVl64pKO5Jq0BLmg\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/index-76306c3d.mjs"
  },
  "/_nuxt/index-888d3d56.mjs": {
    "type": "application/javascript",
    "etag": "\"39c-9o7+6t9NKaufIUjpJC3Jjc3wyeo\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/index-888d3d56.mjs"
  },
  "/_nuxt/index-933adccb.mjs": {
    "type": "application/javascript",
    "etag": "\"21-9spEOGVcmW89hqFrYBxSkrRDzAU\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/index-933adccb.mjs"
  },
  "/_nuxt/index-b7312731.mjs": {
    "type": "application/javascript",
    "etag": "\"21-9spEOGVcmW89hqFrYBxSkrRDzAU\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/index-b7312731.mjs"
  },
  "/_nuxt/login-1de5e9d9.mjs": {
    "type": "application/javascript",
    "etag": "\"888-26gT5078tZlh8bz8bKQo7Isgn3s\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/login-1de5e9d9.mjs"
  },
  "/_nuxt/manifest.json": {
    "type": "application/json",
    "etag": "\"e9f-/wHRfGecfC5l1dneNhtnw9sOjqY\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/manifest.json"
  },
  "/_nuxt/mdi-3b8b1722.mjs": {
    "type": "application/javascript",
    "etag": "\"44b-o69nopo49C46i1UfR/JGWFdeDvE\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/mdi-3b8b1722.mjs"
  },
  "/_nuxt/register-8fda7a05.mjs": {
    "type": "application/javascript",
    "etag": "\"b5a-QX/YlIhlRgHutDy4nnuurPmhwII\"",
    "mtime": "2022-06-27T10:05:40.742Z",
    "path": "../public/_nuxt/register-8fda7a05.mjs"
  }
};

const mainDir = dirname(fileURLToPath(globalThis.entryURL));
function readAsset (id) {
  return promises.readFile(resolve(mainDir, assets[id].path)).catch(() => {})
}

const publicAssetBases = ["/_nuxt"];

function isPublicAssetURL(id = '') {
  if (assets[id]) {
    return
  }
  for (const base of publicAssetBases) {
    if (id.startsWith(base)) { return true }
  }
  return false
}

function getAsset (id) {
  return assets[id]
}

const METHODS = ["HEAD", "GET"];
const _static = eventHandler(async (event) => {
  if (event.req.method && !METHODS.includes(event.req.method)) {
    return;
  }
  let id = decodeURIComponent(withLeadingSlash(withoutTrailingSlash(parseURL(event.req.url).pathname)));
  let asset;
  for (const _id of [id, id + "/index.html"]) {
    const _asset = getAsset(_id);
    if (_asset) {
      asset = _asset;
      id = _id;
      break;
    }
  }
  if (!asset) {
    if (isPublicAssetURL(id)) {
      throw createError({
        statusMessage: "Cannot find static asset " + id,
        statusCode: 404
      });
    }
    return;
  }
  const ifNotMatch = event.req.headers["if-none-match"] === asset.etag;
  if (ifNotMatch) {
    event.res.statusCode = 304;
    event.res.end("Not Modified (etag)");
    return;
  }
  const ifModifiedSinceH = event.req.headers["if-modified-since"];
  if (ifModifiedSinceH && asset.mtime) {
    if (new Date(ifModifiedSinceH) >= new Date(asset.mtime)) {
      event.res.statusCode = 304;
      event.res.end("Not Modified (mtime)");
      return;
    }
  }
  if (asset.type) {
    event.res.setHeader("Content-Type", asset.type);
  }
  if (asset.etag) {
    event.res.setHeader("ETag", asset.etag);
  }
  if (asset.mtime) {
    event.res.setHeader("Last-Modified", asset.mtime);
  }
  const contents = await readAsset(id);
  event.res.end(contents);
});

export { _static as default };
//# sourceMappingURL=static.mjs.map
