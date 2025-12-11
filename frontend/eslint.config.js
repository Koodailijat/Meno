import { tanstackConfig } from '@tanstack/eslint-config'
import perfectionist from 'eslint-plugin-perfectionist'
import { defineConfig } from 'eslint/config'

export default defineConfig([
  {
    ignores: ['*.config.js'],
  },
  ...tanstackConfig,
  perfectionist.configs['recommended-natural'],
  {
    rules: {
      '@typescript-eslint/array-type': ['error', { default: 'array' }],
      'import/order': 'off',
      // Disable conflicting rules from other plugins
      'sort-imports': 'off',
      'sort-keys': 'off',
    },
  },
])
